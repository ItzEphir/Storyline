package com.ephirium.storyline.presentation.mapper

import com.ephirium.data.storage.PostDto
import com.ephirium.storyline.model.Post

fun convert(posts: List<PostDto>): Sequence<Post> =
    sequence { posts.forEach { yield(Post(it.name, it.description, it.author, "${it.id}.jpg")) } }