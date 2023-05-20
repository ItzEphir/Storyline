package com.ephirium.storyline.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.common.log.logError
import com.ephirium.data.repository.PostRepository
import com.ephirium.data.storage.PostDto
import com.ephirium.domain.usecase.PostUseCase
import com.ephirium.storyline.model.Post
import com.ephirium.storyline.presentation.mapper.convert

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val mutPosts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = mutPosts

    private val postUseCase = PostUseCase(PostRepository())

    fun observePosts() {
        postUseCase.observePosts(object : DataConstListener<List<PostDto>>{
            override fun onChange(value: List<PostDto>) {
                mutPosts.postValue(convert(value).toList())
            }
        }, object : ErrorListener{
            override fun onError(exception: Exception) {
                logError(exception.toString())
            }
        })
    }

}