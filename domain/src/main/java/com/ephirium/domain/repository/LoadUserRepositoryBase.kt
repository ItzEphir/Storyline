package com.ephirium.domain.repository

import android.content.SharedPreferences
import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.domain.dto.UserDtoBase

interface LoadUserRepositoryBase<T : UserDtoBase?>{

    val sharedPreferences: SharedPreferences

    fun observe(email: String, dataListener: DataConstListener<T>, errorListener: ErrorListener)

    suspend fun observeSharedPref(id: String, dataListener: DataConstListener<String?>)

}