package com.ephirium.storyline.ui.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.ephirium.common.log.log
import com.ephirium.storyline.databinding.ViewPostBinding
import com.ephirium.storyline.model.Post
import com.ephirium.storyline.ui.recycler.callback.PostCallback
import com.ephirium.storyline.ui.recycler.holder.PostViewHolder
import kotlin.math.max

class PostAdapter(private val callback: PostCallback) : Adapter<PostViewHolder>() {

    var posts: List<Post> = ArrayList()
        set(value) {
            log("Done")
            val size = itemCount
            field = ArrayList(value)
            notifyItemRangeChanged(0, max(size, itemCount))
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        log("Well")
        return PostViewHolder(
            callback,
            ViewPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        log("Excellent")
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size

}

