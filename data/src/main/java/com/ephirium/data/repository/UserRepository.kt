package com.ephirium.data.repository

import com.ephirium.data.storage.UserDto
import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.domain.repository.UserRepositoryBase
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository : UserRepositoryBase<UserDto> {
    override fun observe(dataListener: DataConstListener<List<UserDto>>, errorListener: ErrorListener) {
        FirebaseFirestore.getInstance().collection("users").get()
            .addOnSuccessListener {
                dataListener.onChange(it.toObjects(UserDto::class.java))
            }
            .addOnFailureListener {
                errorListener.onError(it)
            }
    }

}