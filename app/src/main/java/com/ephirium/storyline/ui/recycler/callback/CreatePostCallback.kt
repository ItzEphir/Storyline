package com.ephirium.storyline.ui.recycler.callback

class CreatePostCallback() {
    private val onClickCallbacks: MutableList<OnClickCallback<Nothing>> = ArrayList()

    constructor(onClickCallback: OnClickCallback<Nothing>) : this(){
        onClickCallbacks += onClickCallback
    }

    fun onClick(){
        onClickCallbacks.forEach {
            it.onClick(null)
        }
    }
}