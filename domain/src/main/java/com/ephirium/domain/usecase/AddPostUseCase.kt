package com.ephirium.domain.usecase

import com.ephirium.common.types.OnExceptionListener
import com.ephirium.common.types.OnSuccessListener
import com.ephirium.domain.dto.PostDtoBase
import com.ephirium.domain.repository.AddPostRepositoryBase

class AddPostUseCase<T : PostDtoBase>(val repository: AddPostRepositoryBase<T>) {
    fun addPost(
        post: T,
        onSuccessListener: OnSuccessListener,
        onExceptionListener: OnExceptionListener
    ) {
        repository.addPost(post, onSuccessListener, onExceptionListener)
    }
}