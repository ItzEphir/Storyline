package com.ephirium.data.repository

import com.ephirium.domain.repository.ChapterRepositoryBase
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class ChapterRepository : ChapterRepositoryBase<File> {

    private val htmls = FirebaseStorage.getInstance().reference.child("htmls")

    override fun observe(
        postId: String,
        source: String,
        onChangeListener: (value: File) -> Unit,
        onErrorListener: (exception: Exception) -> Unit
    ) {
        val localFile = File.createTempFile("chapter.$postId.$source", "html")
        htmls.child(postId).child("$source.html").getFile(localFile).addOnSuccessListener {
            onChangeListener(localFile)
        }.addOnFailureListener {
            onErrorListener(it)
        }
    }

}