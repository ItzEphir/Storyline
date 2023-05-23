package com.ephirium.domain.usecase

import android.net.Uri
import com.ephirium.common.types.OnExceptionListener
import com.ephirium.common.types.OnSuccessListener
import com.ephirium.domain.repository.SavePostImageRepositoryBase

class SavePostImageUseCase(val repository: SavePostImageRepositoryBase) {
    fun savePostImageViaUri(
        postId: String,
        uri: Uri,
        mimeType: String,
        onSuccessListener: OnSuccessListener,
        onExceptionListener: OnExceptionListener
    ) {
        repository.saveImageViaUri(postId, uri, mimeType, onSuccessListener, onExceptionListener)
    }
}