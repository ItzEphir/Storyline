package com.ephirium.storyline.ui.recycler.holder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ephirium.storyline.databinding.ViewAddChapterBinding
import com.ephirium.storyline.ui.recycler.callback.AddChapterCallback

class AddChapterViewHolder(
    private val binding: ViewAddChapterBinding,
    private val callback: AddChapterCallback
) : ViewHolder(binding.root) {
    fun bind(){

        binding.addChapterButton.setOnClickListener {
            callback.onAddButtonClick(binding.chapterTitle.text.toString())
        }
    }
}