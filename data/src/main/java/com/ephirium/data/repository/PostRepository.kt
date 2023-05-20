package com.ephirium.data.repository

import com.ephirium.data.storage.PostDto
import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.domain.repository.PostRepositoryBase
import com.google.firebase.firestore.FirebaseFirestore

class PostRepository : PostRepositoryBase<PostDto> {
    override fun observe(dataListener: DataConstListener<List<PostDto>>, errorListener: ErrorListener) {
        FirebaseFirestore.getInstance().collection("posts").get()
            .addOnSuccessListener {
                dataListener.onChange(it.toObjects(PostDto::class.java))
            }.addOnFailureListener {
                errorListener.onError(it)
            }
    }
}