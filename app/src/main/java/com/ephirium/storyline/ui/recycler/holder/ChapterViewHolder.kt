package com.ephirium.storyline.ui.recycler.holder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ephirium.storyline.databinding.ViewConstChapterBinding
import com.ephirium.storyline.model.Chapter
import com.ephirium.storyline.ui.recycler.callback.ChapterCallback

class ChapterViewHolder(
    private val callback: ChapterCallback,
    private val binding: ViewConstChapterBinding
) : ViewHolder(binding.root) {
    fun bind(chapter: Chapter) {
        binding.title.text = chapter.name

        binding.root.setOnClickListener {
            callback.onClick(chapter)
        }
    }
}