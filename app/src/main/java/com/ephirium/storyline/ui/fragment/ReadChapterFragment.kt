package com.ephirium.storyline.ui.fragment

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ephirium.common.log.logError
import com.ephirium.data.repository.ChapterRepository
import com.ephirium.data.storage.PostDto
import com.ephirium.domain.usecase.ChapterUseCase
import com.ephirium.storyline.R
import com.ephirium.storyline.databinding.FragmentReadChapterBinding
import com.ephirium.storyline.presentation.PostViewModel
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader


class ReadChapterFragment : Fragment(R.layout.fragment_read_chapter) {

    private val binding: FragmentReadChapterBinding by viewBinding()

    private val postViewModel: PostViewModel by activityViewModels()

    private val chapterUseCase = ChapterUseCase<PostDto, File>(ChapterRepository())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postViewModel.chosenChapter.observe(requireActivity()) { chapter ->
            chapter?.let { chapterNotNull ->
                binding.chapterTitle.title = chapterNotNull.name

                postViewModel.chosenPost.observe(requireActivity()) { post ->
                    post?.let {
                        chapterUseCase.loadChapter(
                            post.connectedPostDto,
                            chapterNotNull.index,
                            { file ->
                                readHtml(
                                    file,
                                    { value ->
                                        binding.text.text =
                                            Html.fromHtml(value, Html.FROM_HTML_MODE_COMPACT)
                                    },
                                    { exception -> logError(exception.toString()) })
                            },
                            {
                                logError(it.toString())
                            })
                    }
                }
            }
        }

    }

    private fun readHtml(
        file: File,
        resultListener: (value: String) -> Unit,
        errorListener: (exception: Exception) -> Unit
    ) {
        try {
            val fis = FileInputStream(file)
            val reader = BufferedReader(InputStreamReader(fis))
            var line: String?
            val text = StringBuilder()
            while (reader.readLine().also { line = it } != null) {
                text.append(line)
                text.append('\n')
            }
            resultListener(text.toString())
        } catch (e: IOException) {
            errorListener(e)
        }
    }
}

