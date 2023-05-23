package com.ephirium.storyline.model

import com.ephirium.storyline.common.delegate.DelegateItem

class AddChapterItem : DelegateItem {
    override fun getDelegateId(): String = "addChapter"

    override fun getDelegateContent(): String = "addChapter"
}