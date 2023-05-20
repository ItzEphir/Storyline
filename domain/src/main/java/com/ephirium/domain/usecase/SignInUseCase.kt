package com.ephirium.domain.usecase

import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.domain.dto.UserDtoBase
import com.ephirium.domain.repository.SignInRepositoryBase

class SignInUseCase<T : UserDtoBase?>(val repository: SignInRepositoryBase<T>) {
    fun signIn(login: String, password: String, dataConstListener: DataConstListener<T?>, errorListener: ErrorListener){
        repository.signIn(login, password, dataConstListener, errorListener)
    }
}