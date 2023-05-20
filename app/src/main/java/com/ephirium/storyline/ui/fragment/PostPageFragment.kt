package com.ephirium.storyline.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ephirium.common.log.log
import com.ephirium.storyline.R
import com.ephirium.storyline.databinding.FragmentPostPageBinding
import com.ephirium.storyline.presentation.PostViewModel
import com.ephirium.storyline.ui.recycler.adapter.PostAdapter
import com.ephirium.storyline.ui.recycler.callback.PostCallback

class PostPageFragment : Fragment(R.layout.fragment_post_page) {
    private val binding: FragmentPostPageBinding by viewBinding()

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[PostViewModel::class.java]
    }

    private val callback = PostCallback {

    }.addOnSwipeCallback { direction, position ->

    }
    private val adapter = PostAdapter(callback)

    private val itemTouchHelperCallback = object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            val dragFlags =
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            val swipeFlags = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
            return makeMovementFlags(dragFlags, swipeFlags)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            callback.onMove(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            callback.onSwipe(direction, viewHolder.adapterPosition)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        log("PostPage")

        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.recycler)

        binding.recycler.adapter = adapter

        if (savedInstanceState == null) {
            viewModel.observePosts()
        }
        viewModel.posts.observe(requireActivity()) { posts -> adapter.posts = posts }
    }
}