package com.ephirium.storyline.model

import com.ephirium.storyline.common.delegate.DelegateItem

data class User(val login: String, val email: String, val displayName: String, val description: String) : DelegateItem{
    override fun getDelegateId(): String = login
    override fun getDelegateContent(): String = email + displayName + description
}
