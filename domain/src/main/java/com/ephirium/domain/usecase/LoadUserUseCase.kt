package com.ephirium.domain.usecase

import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.domain.dto.UserDtoBase
import com.ephirium.domain.repository.LoadUserRepositoryBase
import kotlinx.coroutines.runBlocking

class LoadUserUseCase<T : UserDtoBase?>(private val repository: LoadUserRepositoryBase<T>) {

    @Suppress("Unused")
    fun observeFromSharedPreferences(id: String, dataListener: DataConstListener<String?>) =
        runBlocking {
            repository.observeSharedPref(id, dataListener)
        }

    @Suppress("Unused")
    fun observeUser(
        email: String,
        dataListener: DataConstListener<T>,
        errorListener: ErrorListener
    ) {
        repository.observe(email, dataListener, errorListener)
    }

    fun observeUserFromSharedPref(
        id: String,
        dataListener: DataConstListener<T?>,
        errorListener: ErrorListener
    ) = runBlocking {
        repository.observeSharedPref(id, object : DataConstListener<String?> {
            override fun onChange(value: String?) {
                when (value) {
                    null -> dataListener.onChange(null)
                    else -> {
                        repository.observe(value, object : DataConstListener<T> {
                            override fun onChange(value: T) {
                                dataListener.onChange(value)
                            }
                        }, errorListener)
                    }
                }
            }

        })
    }
}