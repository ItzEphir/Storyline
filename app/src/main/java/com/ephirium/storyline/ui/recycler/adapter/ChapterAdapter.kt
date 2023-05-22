package com.ephirium.storyline.ui.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.ephirium.storyline.databinding.ViewConstChapterBinding
import com.ephirium.storyline.model.Chapter
import com.ephirium.storyline.ui.recycler.callback.ChapterCallback
import com.ephirium.storyline.ui.recycler.holder.ChapterViewHolder
import kotlin.math.max

class ChapterAdapter(private val callback: ChapterCallback) : Adapter<ChapterViewHolder>() {

    var chapters: List<Chapter> = ArrayList()
        set(value) {
            val size = itemCount
            field = ArrayList(value)
            notifyItemRangeChanged(0, max(size, itemCount))
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        return ChapterViewHolder(
            callback,
            ViewConstChapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        holder.bind(chapters[position])
    }

    override fun getItemCount(): Int = chapters.size

}