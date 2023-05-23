package com.ephirium.storyline.ui.recycler.holder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ephirium.storyline.databinding.ViewCreatePostBinding
import com.ephirium.storyline.ui.recycler.callback.CreatePostCallback

class CreatePostViewHolder(
    private val binding: ViewCreatePostBinding, private val callback: CreatePostCallback
) : ViewHolder(binding.root) {

    fun bind() {

        binding.root.alpha = 1.0f
        binding.root.setOnClickListener { callback.onClick() }
    }
}