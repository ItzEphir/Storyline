package com.ephirium.domain.usecase

import com.ephirium.common.log.log
import com.ephirium.domain.repository.SaveUserRepositoryBase
import kotlinx.coroutines.runBlocking

class SaveUserUseCase(val repository: SaveUserRepositoryBase) {
    fun saveUser(key: String, data: String) = runBlocking {
        log("Trying to save...")
        repository.saveToSharedPref(key, data)
    }
}