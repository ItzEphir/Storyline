package com.ephirium.data.repository

import com.ephirium.common.listener.DataChangeListener
import com.ephirium.common.listener.DataChangeListenerImpl
import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.common.log.logError
import com.ephirium.data.storage.PostDto
import com.ephirium.data.storage.UserDto
import com.ephirium.domain.repository.ProfileRepositoryBase
import com.ephirium.domain.repository.UserRepositoryBase
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class ProfileRepository(
    override val profile: UserDto,
    override val userRepository: UserRepositoryBase<UserDto>
) : ProfileRepositoryBase<UserDto, PostDto> {
    override fun observe(dataListener: DataConstListener<List<PostDto>>, errorListener: ErrorListener) {
        userRepository.observe(object : DataConstListener<List<UserDto>> {
            override fun onChange(value: List<UserDto>) {
                for (v in value) {
                    if (v.id != profile.id) continue

                    val dataChangeListener = DataChangeListenerImpl<PostDto>(ArrayList())

                    observePosts(dataChangeListener, v.posts, dataListener)
                }
            }

        },
            object : ErrorListener {
                override fun onError(exception: Exception) {
                    this logError exception.toString()
                }

            })
    }

    private fun observePosts(
        dataChangeListener: DataChangeListener<PostDto>,
        postsIds: List<String>,
        dataListener: DataConstListener<List<PostDto>>
    ) {
        for(postId in postsIds){
            FirebaseFirestore.getInstance().collection("posts").document(postId).addSnapshotListener { value, error ->
                run {
                    if (error != null) {
                        logError(error.toString())
                        return@run
                    }

                    value?.toObject(PostDto::class.java)?.let { dataChangeListener.onAdd(it) }

                    dataListener.onChange(dataChangeListener.data)

                }
            }
        }
    }
}