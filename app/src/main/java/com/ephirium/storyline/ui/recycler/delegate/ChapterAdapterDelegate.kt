package com.ephirium.storyline.ui.recycler.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ephirium.storyline.common.delegate.AdapterDelegate
import com.ephirium.storyline.common.delegate.DelegateItem
import com.ephirium.storyline.databinding.ViewChapterBinding
import com.ephirium.storyline.model.Chapter
import com.ephirium.storyline.ui.recycler.callback.ChapterCallback
import com.ephirium.storyline.ui.recycler.holder.ChapterViewHolder


class ChapterAdapterDelegate(private val callback: ChapterCallback) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding: ViewChapterBinding =
            ViewChapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChapterViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem) =
        (holder as ChapterViewHolder).bind(item as Chapter)

    override fun isOfViewType(item: DelegateItem): Boolean = item is Chapter
}