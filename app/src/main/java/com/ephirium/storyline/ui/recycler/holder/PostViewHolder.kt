package com.ephirium.storyline.ui.recycler.holder

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.common.log.logError
import com.ephirium.data.repository.PostImageRepository
import com.ephirium.domain.usecase.PostImageUseCase
import com.ephirium.storyline.databinding.ViewPostBinding
import com.ephirium.storyline.model.Post
import com.ephirium.storyline.ui.recycler.callback.PostCallback
import com.squareup.picasso.Picasso

class PostViewHolder(private val callback: PostCallback, private val binding: ViewPostBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val postImageUseCase = PostImageUseCase(PostImageRepository())

    fun bind(post: Post) {
        binding.progressBar.visibility = View.VISIBLE
        if (post.drawable == null) {
            postImageUseCase.observeImage(post.source,
                object : DataConstListener<Uri> {
                    override fun onChange(value: Uri) {
                        Picasso.get().load(value).into(binding.image)
                        post.drawable = binding.image.drawable
                        binding.progressBar.visibility = View.GONE
                    }

                }, object : ErrorListener {
                    override fun onError(exception: Exception) {
                        logError("Error in downloading image")
                    }
                })
        }
        else{
            binding.image.setImageDrawable(post.drawable)
            binding.progressBar.visibility = View.GONE
        }
        binding.layout.setOnClickListener{
            callback.onClick(post)
        }
        binding.name.text = post.name
        binding.description.text = post.description
    }
}