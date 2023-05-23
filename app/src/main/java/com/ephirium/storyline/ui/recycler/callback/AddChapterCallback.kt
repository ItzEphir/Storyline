package com.ephirium.storyline.ui.recycler.callback

class AddChapterCallback() {

    private val onClickCallbacks: MutableList<OnClickCallback<Nothing>> = ArrayList()

    private val onAddButtonClickCallbacks: MutableList<OnClickCallback<String>> = ArrayList()

    @Suppress("Unused")
    constructor(onClickCallback: OnClickCallback<Nothing>) : this() {
        onClickCallbacks.add(onClickCallback)
    }

    @Suppress("Unused")
    fun addOnClickCallback(onClickCallback: OnClickCallback<Nothing>): AddChapterCallback {
        onClickCallbacks.add(onClickCallback)
        return this
    }

    @Suppress("Unused")
    fun addOnAddButtonClickCallback(onAddButtonClickCallback: OnClickCallback<String>): AddChapterCallback {
        onAddButtonClickCallbacks.add(onAddButtonClickCallback)
        return this
    }

    @Suppress("Unused")
    fun onClick() {
        onClickCallbacks.forEach {
            it.onClick(null)
        }
    }

    @Suppress("Unused")
    fun onAddButtonClick(chapterName: String) {
        onAddButtonClickCallbacks.forEach {
            it.onClick(chapterName)
        }
    }

}