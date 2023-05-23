package com.ephirium.storyline.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ephirium.common.log.log
import com.ephirium.storyline.R
import com.ephirium.storyline.databinding.FragmentReadPostBinding
import com.ephirium.storyline.presentation.PostViewModel
import com.ephirium.storyline.ui.recycler.adapter.ChapterAdapter
import com.ephirium.storyline.ui.recycler.callback.ChapterCallback

class ReadPostFragment : Fragment(R.layout.fragment_read_post) {
    private var binding: FragmentReadPostBinding? = null
    private val postViewModel: PostViewModel by activityViewModels()

    private val adapter =
        ChapterAdapter(ChapterCallback { chapter ->
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
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding =
            FragmentReadPostBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        log("Launch")

        log(postViewModel.chosenPost.value.toString())

        binding?.let { binding ->
            postViewModel.chosenPost.observe(requireActivity()) {
                it?.let {
                    adapter.chapters = it.chapters.toList()
                    binding.toolbar.title = it.name
                }
            }

            binding.recycler.adapter = adapter
        }
    }

    override fun onDestroyView() {
        binding = null
        postViewModel.chosenPost.removeObservers(requireActivity())
        super.onDestroyView()
    }
}