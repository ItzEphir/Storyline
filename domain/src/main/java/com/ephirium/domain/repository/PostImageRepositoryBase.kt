package com.ephirium.domain.repository

import android.net.Uri
import com.ephirium.common.listener.DataConstListener
import com.ephirium.common.listener.ErrorListener

interface PostImageRepositoryBase<T : Uri>{
    fun observe(source: String, dataListener: DataConstListener<T>, errorListener: ErrorListener)
}