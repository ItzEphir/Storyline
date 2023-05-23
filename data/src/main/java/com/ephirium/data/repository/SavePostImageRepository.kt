package com.ephirium.data.repository

import android.net.Uri
import com.ephirium.common.types.OnExceptionListener
import com.ephirium.common.types.OnSuccessListener
import com.ephirium.domain.repository.SavePostImageRepositoryBase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storageMetadata


class SavePostImageRepository : SavePostImageRepositoryBase {
    val posts = FirebaseStorage.getInstance().reference.child("posts")
    override fun saveImageViaUri(
        postId: String,
        uri: Uri,
        mimeType: String,
        onSuccessListener: OnSuccessListener,
        onExceptionListener: OnExceptionListener
    ) {
        posts.child("$postId.${mimeType.split("/").last()}").putFile(uri, storageMetadata { contentType = mimeType })
            .addOnSuccessListener {
                onSuccessListener()
            }.addOnFailureListener(onExceptionListener)
    }


}