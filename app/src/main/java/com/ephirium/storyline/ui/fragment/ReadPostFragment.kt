package com.ephirium.storyline.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ephirium.storyline.R
import com.ephirium.storyline.databinding.FragmentReadPostBinding
import com.ephirium.storyline.presentation.PostViewModel
import com.ephirium.storyline.ui.recycler.adapter.ChapterAdapter
import com.ephirium.storyline.ui.recycler.callback.ChapterCallback

class ReadPostFragment : Fragment(R.layout.fragment_read_post) {
    private val binding: FragmentReadPostBinding by lazy {
        FragmentReadPostBinding.bind(requireView())
    }

    private val postViewModel: PostViewModel by activityViewModels()

    private val adapter = ChapterAdapter(ChapterCallback {chapter ->
        postViewModel.chosenPost.observe(requireActivity()) { post ->
            post?.let {
                postViewModel.updateChosenChapter(chapter)
                findNavController().navigate(R.id.action_readPostFragment_to_readChapterFragment)
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = inflater.inflate(R.layout.fragment_read_post, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            postViewModel.chosenPost.observe(requireActivity()) {
                it?.let {
                    adapter.chapters = it.chapters.toList()
                    binding.toolbar.title = it.name
                }
            }

            binding.recycler.adapter = adapter
        }
    }
}