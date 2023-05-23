package com.ephirium.storyline.common.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder

interface AdapterDelegate {
    fun onCreateViewHolder(parent: ViewGroup): ViewHolder
    fun onBindViewHolder(holder: ViewHolder, item: DelegateItem)
    fun isOfViewType(item: DelegateItem): Boolean
}
