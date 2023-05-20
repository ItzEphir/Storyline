package com.ephirium.storyline.model

import android.text.Spannable
import com.ephirium.storyline.common.delegate.DelegateItem

data class Chapter(val name: String, val text: Spannable) : DelegateItem {
    override fun getDelegateId(): String = name

    override fun getDelegateContent(): String = text.toString()
}