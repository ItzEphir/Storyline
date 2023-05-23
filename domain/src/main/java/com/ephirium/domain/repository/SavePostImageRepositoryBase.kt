package com.ephirium.domain.repository

import android.net.Uri
import com.ephirium.common.types.OnExceptionListener
import com.ephirium.common.types.OnSuccessListener

interface SavePostImageRepositoryBase {
    fun saveImageViaUri(
        postId: String,
        uri: Uri,
        mimeType: String,
        onSuccessListener: OnSuccessListener,
        onExceptionListener: OnExceptionListener
    )
}