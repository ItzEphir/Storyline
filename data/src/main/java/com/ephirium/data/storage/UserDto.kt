package com.ephirium.data.storage

import com.ephirium.domain.dto.UserDtoBase
import com.google.firebase.firestore.DocumentId

data class UserDto(
    @DocumentId
    override var id: String = "",
    override var login: String = "",
    override var email: String = "",
    override var displayName: String = "",
    override var description: String = "",
    override var posts: List<String> = ArrayList(),
) : UserDtoBase

