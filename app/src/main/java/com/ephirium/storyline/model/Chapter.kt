package com.ephirium.storyline.model

import com.ephirium.storyline.common.delegate.DelegateItem

data class Chapter(var name: String, var index: Int) : DelegateItem {
    override fun getDelegateId(): String = index.toString()

    override fun getDelegateContent(): String = name
}