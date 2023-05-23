package com.ephirium.data.repository

import com.ephirium.common.types.OnExceptionListener
import com.ephirium.common.types.OnSuccessListener
import com.ephirium.data.storage.PostDto
import com.ephirium.domain.repository.AddPostRepositoryBase
import com.google.firebase.firestore.FirebaseFirestore

class AddPostRepository : AddPostRepositoryBase<PostDto> {
    override fun addPost(
        post: PostDto,
        onSuccessListener: OnSuccessListener,
        onExceptionListener: OnExceptionListener
    ) {
        val userData = hashMapOf(
            "author" to post.author,
            "chapters" to post.chapters,
            "description" to post.description,
            "name" to post.name,
        )
        val doc = FirebaseFirestore.getInstance().collection("posts").document()
        doc.set(userData).addOnSuccessListener {
            post.id = doc.id
            onSuccessListener()
        }.addOnFailureListener {
            doc.delete()
            onExceptionListener(it)
        }
    }

}