package com.ephirium.storyline.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ephirium.storyline.R
import com.ephirium.storyline.databinding.FragmentPostPageBinding
import com.ephirium.storyline.presentation.PostViewModel
import com.ephirium.storyline.ui.recycler.adapter.PostAdapter
import com.ephirium.storyline.ui.recycler.callback.PostCallback

class PostPageFragment : Fragment(R.layout.fragment_post_page) {
    private val binding: FragmentPostPageBinding by viewBinding()

    private val viewModel: PostViewModel by lazy {
        ViewModelProvider(requireActivity())[PostViewModel::class.java]
    }

    private val mainNavController by lazy {
        (requireActivity().supportFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment).navController
    }

    private val callback = PostCallback {
        viewModel.updateChosenPost(it)
        mainNavController.navigate(R.id.action_tabFragment_to_read_post_graph)
    }.addOnSwipeCallback { direction, position ->

    }

    private val adapter = PostAdapter(callback)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.adapter = adapter

        if (savedInstanceState == null) {
            viewModel.observePosts()
        }

        viewModel.posts.observe(requireActivity()) { posts ->
            adapter.posts = posts
        }
    }
}