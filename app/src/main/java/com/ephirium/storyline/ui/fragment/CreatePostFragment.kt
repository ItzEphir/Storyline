package com.ephirium.storyline.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ephirium.common.log.log
import com.ephirium.common.log.logError
import com.ephirium.data.storage.PostDto
import com.ephirium.storyline.R
import com.ephirium.storyline.common.adapter.BaseAdapter
import com.ephirium.storyline.common.delegate.DelegateItem
import com.ephirium.storyline.databinding.FragmentCreatePostBinding
import com.ephirium.storyline.model.AddChapterItem
import com.ephirium.storyline.model.Chapter
import com.ephirium.storyline.model.CreatePostItem
import com.ephirium.storyline.presentation.CreatePostViewModel
import com.ephirium.storyline.presentation.MainViewModel
import com.ephirium.storyline.ui.recycler.callback.AddChapterCallback
import com.ephirium.storyline.ui.recycler.callback.ChapterCallback
import com.ephirium.storyline.ui.recycler.callback.CreatePostCallback
import com.ephirium.storyline.ui.recycler.delegate.AddChapterAdapterDelegate
import com.ephirium.storyline.ui.recycler.delegate.ChapterAdapterDelegate
import com.ephirium.storyline.ui.recycler.delegate.CreatePostAdapterDelegate
import java.util.UUID


class CreatePostFragment : Fragment(R.layout.fragment_create_post) {

    private val binding: FragmentCreatePostBinding by viewBinding()

    private val adapter = BaseAdapter()

    private val createPostViewModel: CreatePostViewModel by activityViewModels()

    private val mainViewModel: MainViewModel by activityViewModels()

    private val createPostItem = CreatePostItem()

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK
            && result.data != null && result.data!!.data != null
        ) {
            val photoUri = result.data!!.data!!
            createPostViewModel.updatePostImageUri(photoUri)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.storyTitleLayout.imageButton.setOnClickListener {
            pickImageLauncher.launch(
                Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
            )

            createPostViewModel.setMimeType(requireActivity().contentResolver)
        }

        createPostViewModel.updateEditingPost(
            PostDto(
                id = UUID.randomUUID().toString(),
                author = mainViewModel.currentUser.value!!.id,
                chapters = sequence {
                    createPostViewModel.chapters.value?.forEach {
                        yield(it.name)
                    }
                }.toList()
            )
        )

        adapter.addDelegate(AddChapterAdapterDelegate(AddChapterCallback().addOnAddButtonClickCallback {
            createPostViewModel.addChapter(it)
            adapter.notifyDataSetChanged()
        }))

        adapter.addDelegate(ChapterAdapterDelegate(ChapterCallback { chapter ->
            createPostViewModel.updateEditingChapter(chapter)
            findNavController().navigate(R.id.action_createStoryFragment_to_writeChapterFragment)
        }))

        adapter.addDelegate(CreatePostAdapterDelegate(CreatePostCallback {
            if (!binding.storyTitleLayout.title.text.isNullOrBlank()) {

                createPostViewModel.editingPost.value?.name =
                    binding.storyTitleLayout.title.text.toString()

                createPostViewModel.publish({
                    createPostViewModel.publishImage({
                        log("Successfully added")
                        findNavController().popBackStack()
                    }, {
                        logError(it.toString())
                    })
                }, {
                    logError(it.toString())
                }, {

                })
            }
        }))

        adapter.submitList(getItemsList(listOf()))

        createPostViewModel.chapters.observe(requireActivity()) {
            adapter.submitList(getItemsList(it))
            adapter.notifyDataSetChanged()
        }

        binding.recycler.adapter = adapter
    }

    private fun getItemsList(chaptersList: List<Chapter>): List<DelegateItem> {
        val list = ArrayList<DelegateItem>()
        list.add(AddChapterItem())
        list.addAll(chaptersList)
        list.add(createPostItem)
        return list
    }
}