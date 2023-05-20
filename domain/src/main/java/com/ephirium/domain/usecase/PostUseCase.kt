package com.ephirium.domain.usecase

import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.domain.dto.PostDtoBase
import com.ephirium.domain.repository.PostRepositoryBase

class PostUseCase<T : PostDtoBase>(private val repository: PostRepositoryBase<T>) {
    fun observePosts(
        dataListener: DataConstListener<List<T>>,
        errorListener: ErrorListener
    ) {
        repository.observe(dataListener, errorListener)
    }
}