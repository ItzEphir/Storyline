package com.ephirium.domain.usecase

import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.domain.dto.PostDtoBase
import com.ephirium.domain.dto.UserDtoBase
import com.ephirium.domain.repository.ProfileRepositoryBase

class ProfileUseCase<T : UserDtoBase, K : PostDtoBase>(val profile: UserDtoBase, val repository: ProfileRepositoryBase<T, K>) {

    fun observePosts(dataListener: DataConstListener<List<K>>, errorListener: ErrorListener){
        repository.observe(dataListener, errorListener)
    }

}