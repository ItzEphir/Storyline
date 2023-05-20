package com.ephirium.data.repository

import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.common.log.log
import com.ephirium.common.log.logError
import com.ephirium.data.storage.UserDto
import com.ephirium.domain.repository.SignUpRepositoryBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class SignUpRepository : SignUpRepositoryBase<UserDto?> {
    override fun signUp(
        login: String,
        email: String,
        password: String,
        displayName: String,
        dataListener: DataConstListener<UserDto?>,
        errorListener: ErrorListener
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val docUser = FirebaseFirestore.getInstance().collection("users").document()
                docUser.set(
                    hashMapOf(
                        "login" to login,
                        "email" to email,
                        "displayName" to displayName,
                        "description" to "",
                        "posts" to FieldValue.arrayUnion("")
                    )
                ).addOnFailureListener { exception ->
                    FirebaseAuth.getInstance().currentUser?.delete()
                        ?.addOnCompleteListener {
                            dataListener.onChange(null)
                            log("User was created but deleted")
                        }?.addOnFailureListener {
                            log("Too strange situation")
                            logError(it.toString())
                        }
                    errorListener.onError(exception)
                }
                docUser.get().addOnCompleteListener {
                    if (it.isSuccessful) {
                        log("trying parse")
                        log("parse successful")
                        dataListener.onChange(it.result.toObject(UserDto::class.java))
                    }
                }.addOnFailureListener {
                    errorListener.onError(it)
                }
            }
    }
}