package com.ephirium.storyline.presentation

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ephirium.common.listener.convert
import com.ephirium.common.log.log
import com.ephirium.common.log.logError
import com.ephirium.data.repository.LoadUserRepository
import com.ephirium.data.repository.SaveUserRepository
import com.ephirium.data.storage.UserDto
import com.ephirium.domain.usecase.LoadUserUseCase
import com.ephirium.domain.usecase.SaveUserUseCase

class MainViewModel(application: Application, sharedPreferences: SharedPreferences) :
    AndroidViewModel(application) {

    private val mutCurrentUser = MutableLiveData<UserDto?>()
    val currentUser: LiveData<UserDto?> = mutCurrentUser

    private val loadUserUseCase: LoadUserUseCase<UserDto?> =
        LoadUserUseCase(LoadUserRepository(sharedPreferences))
    private val saveUserUseCase: SaveUserUseCase =
        SaveUserUseCase(SaveUserRepository(sharedPreferences))

    fun observeCurrentUser(key: String) =
        loadUserUseCase.observeUserFromSharedPref(
            key,
            { value: UserDto? ->
                mutCurrentUser.postValue(value)
            }.convert(),
            { exception: Exception ->
                logError(exception.toString())
            }.convert()
        )

    fun saveNewCurrentUser(key: String, user: UserDto) = saveUserUseCase.saveUser(key, user.email)

    class Factory(
        private val application: Application, private val sharedPreferences: SharedPreferences
    ) : ViewModelProvider.AndroidViewModelFactory(application) {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                log("Cool")
                @Suppress("UNCHECKED_CAST") return MainViewModel(
                    application, sharedPreferences
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
