package com.ephirium.storyline.presentation

import android.app.Application
import android.content.ContentResolver
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ephirium.common.log.log
import com.ephirium.common.types.OnExceptionListener
import com.ephirium.common.types.OnFinallyListener
import com.ephirium.common.types.OnSuccessListener
import com.ephirium.data.repository.AddPostRepository
import com.ephirium.data.repository.SaveChapterRepository
import com.ephirium.data.repository.SavePostImageRepository
import com.ephirium.data.storage.PostDto
import com.ephirium.domain.usecase.AddPostUseCase
import com.ephirium.domain.usecase.SaveChapterUseCase
import com.ephirium.domain.usecase.SavePostImageUseCase
import com.ephirium.storyline.model.Chapter
import java.util.Locale

class CreatePostViewModel(application: Application) : AndroidViewModel(application) {

    private val mutEditingPost = MutableLiveData<PostDto?>()
    val editingPost: LiveData<PostDto?> = mutEditingPost
    private val mutChapters = MutableLiveData<MutableList<Chapter>>()
    val chapters: LiveData<MutableList<Chapter>> = mutChapters
    private val mutEditingChapter = MutableLiveData<Chapter?>()
    val editingChapter: LiveData<Chapter?> = mutEditingChapter
    private val mutChapterHtmls = MutableLiveData<MutableList<String>>()
    val chaptersHtmls: LiveData<MutableList<String>> = mutChapterHtmls
    private val mutPostImageUri = MutableLiveData<Uri>()
    @Suppress("Unused")
    val postImageUri: LiveData<Uri> = mutPostImageUri

    private var mimeType: String = ""

    fun setMimeType(contentResolver: ContentResolver) {
        mutPostImageUri.value?.let {
            mimeType = if (it.scheme == ContentResolver.SCHEME_CONTENT) {
                contentResolver.getType(it).toString()
            } else {
                val fileExtension = MimeTypeMap.getFileExtensionFromUrl(
                    it.toString()
                )
                MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.lowercase(Locale.getDefault())
                ).toString()
            }
        }
    }

    private val saveChapterUseCase = SaveChapterUseCase(SaveChapterRepository())

    private val addPostUseCase = AddPostUseCase(AddPostRepository())

    private val savePostImageUseCase = SavePostImageUseCase(SavePostImageRepository())

    fun updateEditingPost(post: PostDto?) {
        mutEditingPost.postValue(post)
    }

    fun addChapter(chapterName: String) {
        when (mutChapters.value) {
            null -> mutChapters.value = mutableListOf(Chapter(chapterName, 0))

            else -> {
                mutChapters.value!!.also {
                    it.add(Chapter(chapterName, mutChapters.value!!.size))
                    mutChapters.value = it
                }
            }
        }

        mutEditingPost.value!!.let { post ->
            log(post.chapters.toString())
            val list = ArrayList<String>()
            mutChapters.value!!.forEach {
                list.add(it.name)
            }
            post.chapters = list
            mutEditingPost.postValue(post)
            log(post.chapters.toString())
            log(mutEditingPost.value!!.chapters.toString())
        }
    }

    fun updateEditingChapter(chapter: Chapter?) {
        mutEditingChapter.value = chapter
    }

    fun addChapterHtml(html: String) {
        when (mutChapterHtmls.value) {
            null -> mutChapterHtmls.value = mutableListOf(html)

            else -> mutChapterHtmls.value!!.also {
                it.add(html)
                mutChapterHtmls.value = it
            }
        }
    }

    fun updatePostImageUri(uri: Uri) {
        mutPostImageUri.postValue(uri)
    }

    private fun publishHtmls(
        onSuccessListener: OnSuccessListener,
        onExceptionListener: OnExceptionListener,
        onFinallyListener: OnFinallyListener
    ) {
        mutChapters.value?.forEach {
            if (mutChapterHtmls.value == null) {
                onExceptionListener(IndexOutOfBoundsException("Htmls not full"))
                return
            }
            try {
                saveChapterUseCase.saveChapter(
                    editingPost.value!!,
                    it.index.toString(),
                    it.name,
                    mutChapterHtmls.value!![it.index],
                    onSuccessListener,
                    onExceptionListener
                )
            } catch (exception: Exception) {
                onExceptionListener(exception)
            } finally {
                onFinallyListener()
            }
        }
    }

    fun publishImage(
        onSuccessListener: OnSuccessListener,
        onExceptionListener: OnExceptionListener
    ) {
        mutPostImageUri.value?.let { uri ->
            mutEditingPost.value?.let { post ->
                savePostImageUseCase.savePostImageViaUri(
                    post.id,
                    uri,
                    mimeType,
                    onSuccessListener,
                    onExceptionListener
                )
            }
        }
    }

    fun publish(
        onSuccessListener: OnSuccessListener,
        onExceptionListener: OnExceptionListener,
        onFinallyListener: OnFinallyListener
    ) {
        mutEditingPost.value?.let {
            it.mimeType = mimeType
            mutEditingPost.postValue(it)
        }
        if (mutEditingPost.value != null) {
            log(mutEditingPost.value!!.chapters.toString())
            addPostUseCase.addPost(
                mutEditingPost.value!!, {
                    publishHtmls(onSuccessListener, onExceptionListener, onFinallyListener)
                },
                onExceptionListener
            )
        } else {
            onExceptionListener(NullPointerException("Editing post is null"))
        }
    }

}