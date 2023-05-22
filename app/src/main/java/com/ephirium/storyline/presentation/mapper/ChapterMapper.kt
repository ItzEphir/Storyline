package com.ephirium.storyline.presentation.mapper

import com.ephirium.storyline.model.Chapter

fun convert(chapters: List<String>): Sequence<Chapter> =
    sequence { chapters.forEachIndexed { index, chaptersName -> yield(Chapter(chaptersName, index)) } }