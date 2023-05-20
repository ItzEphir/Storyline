package com.ephirium.domain.repository

import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.domain.dto.UserDtoBase

interface SignInRepositoryBase<T : UserDtoBase?> {
    fun signIn(
        login: String,
        password: String,
        dataConstListener: DataConstListener<T?>,
        errorListener: ErrorListener
    )
}