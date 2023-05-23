package com.ephirium.storyline.ui.recycler.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ephirium.storyline.common.delegate.AdapterDelegate
import com.ephirium.storyline.common.delegate.DelegateItem
import com.ephirium.storyline.databinding.ViewAddChapterBinding
import com.ephirium.storyline.model.AddChapterItem
import com.ephirium.storyline.ui.recycler.callback.AddChapterCallback
import com.ephirium.storyline.ui.recycler.holder.AddChapterViewHolder

class AddChapterAdapterDelegate(private val callback: AddChapterCallback) : AdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =
            ViewAddChapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddChapterViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem) {
        (holder as AddChapterViewHolder).bind()
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is AddChapterItem
}