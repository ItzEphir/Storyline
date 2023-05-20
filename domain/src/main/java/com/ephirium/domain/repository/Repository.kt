package com.ephirium.domain.repository

import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener

interface Repository<T> {
    fun observe(dataListener: DataConstListener<T>, errorListener: ErrorListener)
}