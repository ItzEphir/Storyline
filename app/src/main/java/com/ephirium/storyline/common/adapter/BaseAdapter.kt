package com.ephirium.storyline.common.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ephirium.storyline.common.delegate.AdapterDelegate
import com.ephirium.storyline.common.delegate.DelegateItem

class BaseAdapter : ListAdapter<DelegateItem, ViewHolder>(object : ItemCallback<DelegateItem>() {
    override fun areItemsTheSame(oldItem: DelegateItem, newItem: DelegateItem) =
        oldItem.delegateId == newItem.delegateId

    override fun areContentsTheSame(oldItem: DelegateItem, newItem: DelegateItem) =
        oldItem.delegateContent == newItem.delegateContent

}) {

    class EmptyViewHolder(context: Context) : ViewHolder(View(context))

    private val delegates: MutableList<AdapterDelegate> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            in 0..Int.MAX_VALUE -> delegates[viewType].onCreateViewHolder(parent)
            else -> EmptyViewHolder(parent.context)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            delegates[getItemViewType(position)].onBindViewHolder(holder, getItem(position))
        } catch (exception: IndexOutOfBoundsException) {
            exception.printStackTrace()
        }
    }

    override fun getItemViewType(position: Int): Int {
        for (i in 0 until delegates.size) {
            if (delegates[i].isOfViewType(currentList[position])) {
                return i
            }
        }
        return -1
    }

    fun addDelegate(delegate: AdapterDelegate) {
        delegates.add(delegate)
    }
}