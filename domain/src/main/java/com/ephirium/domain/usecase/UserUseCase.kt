package com.ephirium.domain.usecase

import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.domain.dto.UserDtoBase
import com.ephirium.domain.repository.UserRepositoryBase

class UserUseCase<T : UserDtoBase>(private val repository: UserRepositoryBase<T>) {
    fun observeUsers(dataListener: DataConstListener<List<T>>, errorListener: ErrorListener){
        repository.observe(dataListener, errorListener)
    }
}