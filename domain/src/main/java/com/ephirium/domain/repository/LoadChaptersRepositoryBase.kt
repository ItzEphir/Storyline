package com.ephirium.domain.repository

import android.text.SpannableString
import com.ephirium.domain.dto.PostDtoBase

interface LoadChaptersRepositoryBase<T : PostDtoBase> {
    fun loadChapters(
        post: T,
        onChangeListener: (value: List<SpannableString>) -> Unit,
        onExceptionListener: (exception: Exception) -> Unit
    )
}