package com.ephirium.domain.usecase

import com.ephirium.common.types.OnExceptionListener
import com.ephirium.common.types.OnSuccessListener
import com.ephirium.domain.dto.PostDtoBase
import com.ephirium.domain.repository.SaveChapterRepositoryBase

class SaveChapterUseCase(val repository: SaveChapterRepositoryBase) {
    fun saveChapter(
        post: PostDtoBase,
        chapterId: String,
        chapterName: String,
        chapterHtml: String,
        onSuccessListener: OnSuccessListener,
        onExceptionListener: OnExceptionListener
    ) {
        repository.saveChapterToDatabase(post.id, chapterId, chapterName, {
            repository.saveChapterToStorage(
                post.id,
                chapterId,
                chapterHtml,
                onSuccessListener,
                onExceptionListener
            )
        }, onExceptionListener)
    }
}