package com.ephirium.storyline.ui.recycler.callback

import com.ephirium.storyline.model.Chapter

class ChapterCallback() {
    private val onClickCallbacks: MutableList<OnClickCallback<Chapter>> = ArrayList()

    constructor(
        onClickCallback: OnClickCallback<Chapter>
    ) : this() {
        onClickCallbacks.add(onClickCallback)
    }

    @Suppress("Unused")
    fun addOnClickCallback(onClickCallback: OnClickCallback<Chapter>) : ChapterCallback {
        onClickCallbacks.add(onClickCallback)
        return this
    }

    fun onClick(chapter: Chapter) {
        onClickCallbacks.forEach {
            it.onClick(chapter)
        }
    }
}