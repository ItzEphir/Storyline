package com.ephirium.data.repository

import android.content.SharedPreferences
import com.ephirium.domain.repository.SaveUserRepositoryBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveUserRepository(override val sharedPreferences: SharedPreferences) : SaveUserRepositoryBase {
    override suspend fun saveToSharedPref(id: String, data: String) {
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            sharedPreferences.edit().putString(id, data).apply()
        }
    }
}