package com.ephirium.domain.usecase

import android.text.SpannableString
import com.ephirium.domain.dto.PostDtoBase
import com.ephirium.domain.repository.LoadChaptersRepositoryBase

class LoadChaptersUseCase<T : PostDtoBase>(private val repository: LoadChaptersRepositoryBase<T>) {
    fun loadChapters(
        post: T,
        onChangeListener: (value: List<SpannableString>) -> Unit,
        onErrorListener: (exception: Exception) -> Unit
    ) {
        repository.loadChapters(post, onChangeListener, onErrorListener)
    }
}