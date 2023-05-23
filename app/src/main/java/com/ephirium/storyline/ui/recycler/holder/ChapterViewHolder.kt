package com.ephirium.storyline.ui.recycler.holder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ephirium.storyline.databinding.ViewChapterBinding
import com.ephirium.storyline.model.Chapter
import com.ephirium.storyline.ui.recycler.callback.ChapterCallback

class ChapterViewHolder(
    private val binding: ViewChapterBinding,
    private val callback: ChapterCallback
) : ViewHolder(binding.root) {
    fun bind(chapter: Chapter){

        binding.title.text = chapter.name

        binding.root.setOnClickListener {
            callback.onClick(chapter)
        }

    }
}