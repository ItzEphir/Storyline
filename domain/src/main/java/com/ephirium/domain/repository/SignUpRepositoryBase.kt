package com.ephirium.domain.repository

import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.domain.dto.UserDtoBase

interface SignUpRepositoryBase<T: UserDtoBase?> {
    fun signUp(
        login: String,
        email: String,
        password: String,
        displayName: String,
        dataListener: DataConstListener<T?>,
        errorListener: ErrorListener
    )
}