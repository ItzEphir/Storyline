package com.ephirium.domain.repository

import android.content.SharedPreferences

interface SaveUserRepositoryBase {
    val sharedPreferences: SharedPreferences
    suspend fun saveToSharedPref(id: String, data:String)
}