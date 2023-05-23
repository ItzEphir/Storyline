package com.ephirium.data.repository

import android.net.Uri
import com.ephirium.data.storage.PostDto
import com.ephirium.domain.repository.SaveChapterRepositoryBase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.io.FileWriter

class SaveChapterRepository : SaveChapterRepositoryBase {
    private val htmls = FirebaseStorage.getInstance().reference.child("htmls")

    private val posts = FirebaseFirestore.getInstance().collection("posts")

    override fun saveChapterToStorage(
        postId: String,
        chaptersIndex: String,
        html: String,
        onSuccessListener: () -> Unit,
        onExceptionListener: (exception: Exception) -> Unit
    ) {
        val file = File.createTempFile("$postId.$chaptersIndex", "html")
        val writer = FileWriter(file)
        writer.write(html)
        writer.close()
        htmls.child(postId).child("$chaptersIndex.html").putFile(Uri.fromFile(file)).addOnSuccessListener {
            onSuccessListener()
        }.addOnFailureListener {
            onExceptionListener(it)
        }
    }

    override fun saveChapterToDatabase(
        postId: String,
        chaptersIndex: String,
        chaptersName: String,
        onSuccessListener: () -> Unit,
        onExceptionListener: (exception: Exception) -> Unit
    ) {
        posts.document(postId).get().addOnSuccessListener { doc ->
            val chapters = doc.toObject(PostDto::class.java)?.chapters
            if (chapters == null) {
                onExceptionListener(NullPointerException("Chapters do not exist"))
            }

            chapters?.let {
                val newChapters = it.toMutableList()
                if (chaptersIndex.toIntOrNull() in chapters.indices) {
                    newChapters[chaptersIndex.toIntOrNull()!!] = chaptersName
                } else {
                    chaptersIndex.toIntOrNull()
                        ?.let { it1 ->
                            if (it1 in chapters.indices)
                                newChapters.add(index = it1, element = chaptersName)
                        }
                }

                posts.document(postId).update("chapters", newChapters).addOnSuccessListener {
                    onSuccessListener()
                }.addOnFailureListener { exception ->
                    onExceptionListener(exception)
                }
            }
        }.addOnFailureListener {
            onExceptionListener(it)
        }
    }
}