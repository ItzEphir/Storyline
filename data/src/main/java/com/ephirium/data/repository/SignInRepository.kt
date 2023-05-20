package com.ephirium.data.repository

import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.common.log.log
import com.ephirium.data.storage.UserDto
import com.ephirium.domain.repository.SignInRepositoryBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignInRepository : SignInRepositoryBase<UserDto> {
    override fun signIn(
        login: String,
        password: String,
        dataConstListener: DataConstListener<UserDto?>,
        errorListener: ErrorListener
    ) {
        FirebaseFirestore.getInstance().collection("users").whereEqualTo("login", login).get()
            .addOnCompleteListener {
                if (it.result.documents.isNotEmpty()) {
                    val userDto = it.result.documents.last().toObject(UserDto::class.java)
                    log((userDto == null).toString())
                    userDto?.let { it1 ->
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(it1.email, password)
                            .addOnCompleteListener {
                                dataConstListener.onChange(userDto)
                            }.addOnCanceledListener {
                                log("cancelled")
                            }.addOnFailureListener { exception ->
                                errorListener.onError(exception)
                            }
                    }
                    if (userDto == null) {
                        dataConstListener.onChange(null)
                    }
                } else {
                    dataConstListener.onChange(null)
                }
            }.addOnFailureListener {
                errorListener.onError(it)
            }
    }

}