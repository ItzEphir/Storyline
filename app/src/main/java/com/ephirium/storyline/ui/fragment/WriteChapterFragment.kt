package com.ephirium.storyline.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ephirium.storyline.R
import com.ephirium.storyline.databinding.FragmentWriteChapterBinding
import com.ephirium.storyline.model.Chapter
import com.ephirium.storyline.presentation.CreatePostViewModel

class WriteChapterFragment : Fragment(R.layout.fragment_write_chapter) {

    private val binding: FragmentWriteChapterBinding by viewBinding()

    private val createPostViewModel: CreatePostViewModel by activityViewModels()

    private var chapter: Chapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createPostViewModel.editingChapter.observe(requireActivity()) {
            chapter = it
        }

        createPostViewModel.chaptersHtmls.observe(requireActivity()) {
            if (chapter?.index != null && it.size < chapter?.index!!) {
                binding.text.text = Editable.Factory.getInstance()
                    .newEditable(Html.fromHtml(it[chapter?.index!!], Html.FROM_HTML_MODE_COMPACT))
            }
        }

        binding.endEditButton.setOnClickListener {
            createPostViewModel.addChapterHtml(
                Html.toHtml(
                    binding.text.text,
                    Html.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE
                )
            )
            findNavController().popBackStack()
        }
    }

}