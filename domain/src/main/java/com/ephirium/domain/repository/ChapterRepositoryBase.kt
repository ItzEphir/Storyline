package com.ephirium.domain.repository

import java.io.File

interface ChapterRepositoryBase<T : File> {
    fun observe(
        postId: String,
        source: String,
        onChangeListener: (value: T) -> Unit,
        onErrorListener: (exception: Exception) -> Unit
    )
}