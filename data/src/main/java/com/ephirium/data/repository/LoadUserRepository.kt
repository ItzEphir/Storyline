package com.ephirium.data.repository

import android.content.SharedPreferences
import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.common.log.logError
import com.ephirium.data.storage.UserDto
import com.ephirium.domain.repository.LoadUserRepositoryBase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadUserRepository(override val sharedPreferences: SharedPreferences) :
    LoadUserRepositoryBase<UserDto?> {
    override fun observe(
        email: String,
        dataListener: DataConstListener<UserDto?>,
        errorListener: ErrorListener
    ) {
        FirebaseFirestore.getInstance().collection("users").whereEqualTo("email", email)
            .addSnapshotListener { value, error ->
                run {
                    error?.let { logError(error.toString()) }

                    value?.let {
                        val data = it.toObjects(UserDto::class.java)
                        if (data.isNotEmpty()) {
                            it.toObjects(UserDto::class.java)[0]?.let { it1 ->
                                dataListener.onChange(
                                    it1
                                )
                            }
                        }
                        else{
                            dataListener.onChange(null)
                        }
                    }
                }
            }
    }

    override suspend fun observeSharedPref(
        id: String,
        dataListener: DataConstListener<String?>
    ) = dataListener.onChange(withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
        sharedPreferences.getString(id, null)
    })
}