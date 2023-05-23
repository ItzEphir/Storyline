package com.ephirium.storyline.model

import com.ephirium.storyline.common.delegate.DelegateItem

class CreatePostItem(var alpha: Float = 0.5f) : DelegateItem {
    override fun getDelegateId(): String = "createPost"
    override fun getDelegateContent(): String = alpha.toString()
}