package com.ephirium.domain.repository

import com.ephirium.common.types.OnExceptionListener
import com.ephirium.common.types.OnSuccessListener

interface SaveChapterRepositoryBase {
    fun saveChapterToStorage(
        postId: String,
        chaptersIndex: String,
        html: String,
        onSuccessListener: OnSuccessListener,
        onExceptionListener: OnExceptionListener
    )

    fun saveChapterToDatabase(
        postId: String,
        chaptersIndex: String,
        chaptersName: String,
        onSuccessListener: OnSuccessListener,
        onExceptionListener: OnExceptionListener
    )
}