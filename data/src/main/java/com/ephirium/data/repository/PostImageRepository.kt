package com.ephirium.data.repository

import android.net.Uri
import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.domain.repository.PostImageRepositoryBase
import com.google.firebase.storage.FirebaseStorage

class PostImageRepository : PostImageRepositoryBase<Uri> {

    private val postsReference = FirebaseStorage.getInstance().reference.child("posts")

    override fun observe(
        source: String,
        dataListener: DataConstListener<Uri>,
        errorListener: ErrorListener
    ) {
        postsReference.child(source).downloadUrl
            .addOnSuccessListener {
                dataListener.onChange(it);
            }.addOnFailureListener {
                errorListener.onError(it)
            }
    }
}