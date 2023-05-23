package com.ephirium.domain.repository

import com.ephirium.common.types.OnExceptionListener
import com.ephirium.common.types.OnSuccessListener
import com.ephirium.domain.dto.PostDtoBase

interface AddPostRepositoryBase<T: PostDtoBase> {
    fun addPost(post: T, onSuccessListener: OnSuccessListener, onExceptionListener: OnExceptionListener)
}