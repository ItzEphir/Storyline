package com.ephirium.domain.usecase

import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.domain.dto.UserDtoBase
import com.ephirium.domain.repository.SignUpRepositoryBase

class SignUpUseCase<T : UserDtoBase?>(val repository: SignUpRepositoryBase<T?>) {
    fun signUp(
        login: String,
        email: String,
        password: String,
        displayName: String,
        dataListener: DataConstListener<T?>,
        errorListener: ErrorListener
    ) = repository.signUp(login, email, password, displayName, dataListener, errorListener)
}