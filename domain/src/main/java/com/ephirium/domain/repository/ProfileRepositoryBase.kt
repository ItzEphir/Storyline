package com.ephirium.domain.repository

import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener
import com.ephirium.domain.dto.PostDtoBase
import com.ephirium.domain.dto.UserDtoBase

interface ProfileRepositoryBase<T : UserDtoBase, K : PostDtoBase> : Repository<List<K>> {
    val profile: T
    val userRepository: UserRepositoryBase<T>
    override fun observe(dataListener: DataConstListener<List<K>>, errorListener: ErrorListener)
}