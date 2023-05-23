package com.ephirium.storyline.ui.recycler.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ephirium.storyline.common.delegate.AdapterDelegate
import com.ephirium.storyline.common.delegate.DelegateItem
import com.ephirium.storyline.databinding.ViewCreatePostBinding
import com.ephirium.storyline.model.CreatePostItem
import com.ephirium.storyline.ui.recycler.callback.CreatePostCallback
import com.ephirium.storyline.ui.recycler.holder.CreatePostViewHolder

class CreatePostAdapterDelegate(val callback: CreatePostCallback) : AdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CreatePostViewHolder(
            ViewCreatePostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), callback
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem) {
        (holder as CreatePostViewHolder).bind()
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is CreatePostItem
}