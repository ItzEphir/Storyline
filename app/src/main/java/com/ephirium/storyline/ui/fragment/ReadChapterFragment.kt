package com.ephirium.storyline.ui.fragment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
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

    private var binding: FragmentReadChapterBinding? = null

    private val postViewModel: PostViewModel by activityViewModels()

    private val chapterUseCase = ChapterUseCase<PostDto, File>(ChapterRepository())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentReadChapterBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            binding?.let { binding ->
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
                                                    Html.fromHtml(
                                                        value,
                                                        Html.FROM_HTML_MODE_COMPACT
                                                    )
                                                binding.text.setTextColor(
                                                    resources.getColor(
                                                        R.color.white,
                                                        null
                                                    )
                                                )
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
        }
    }

    override fun onDestroyView() {
        binding = null
        postViewModel.chosenPost.removeObservers(requireActivity())
        postViewModel.chosenChapter.removeObservers(requireActivity())
        postViewModel.posts.removeObservers(requireActivity())
        super.onDestroyView()
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

