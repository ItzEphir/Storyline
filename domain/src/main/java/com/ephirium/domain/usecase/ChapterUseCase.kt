package com.ephirium.domain.usecase

import com.ephirium.domain.dto.PostDtoBase
import com.ephirium.domain.repository.ChapterRepositoryBase
import java.io.File

class ChapterUseCase<T : PostDtoBase, K : File>(private val repository: ChapterRepositoryBase<K>) {
    fun loadChapter(
        post: T,
        chapter: Int,
        onChangeListener: (value: K) -> Unit,
        onErrorListener: (exception: Exception) -> Unit
    ) {
        repository.observe(post.id, chapter.toString(), onChangeListener, onErrorListener)
    }
}