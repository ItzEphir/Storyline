package com.ephirium.domain.usecase

import android.net.Uri
import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.domain.repository.PostImageRepositoryBase

class PostImageUseCase<T : Uri>(val repository: PostImageRepositoryBase<T>) {

    fun observeImage(source: String, dataListener: DataConstListener<T>, errorListener: ErrorListener){
        repository.observe(source, dataListener, errorListener)
    }
}