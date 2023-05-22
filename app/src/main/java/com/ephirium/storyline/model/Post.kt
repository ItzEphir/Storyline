package com.ephirium.storyline.model

import android.graphics.drawable.Drawable
import com.ephirium.data.storage.PostDto
import com.ephirium.storyline.common.delegate.DelegateItem

data class Post(val name: String, val description: String, val author: String, val source: String, val chapters: Sequence<Chapter>, val connectedPostDto: PostDto) :
    DelegateItem {
    var drawable: Drawable? = null
    override fun getDelegateId(): String = name
    override fun getDelegateContent(): String = description + author + source
}