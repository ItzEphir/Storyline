package com.ephirium.storyline.presentation.mapper

import com.ephirium.data.storage.PostDto
import com.ephirium.storyline.model.Post

fun convert(posts: List<PostDto>): Sequence<Post> =
    sequence {
        posts.forEach {
            yield(
                Post(
                    it.name,
                    it.description,
                    it.author,
                    "${it.id}.${it.mimeType}",
                    convert(it.chapters),
                    it
                )
            )
        }
    }

@Suppress("Unused")
fun PostDto.convert(): Post {
    return Post(
        name = this.name,
        description = this.description,
        author = this.author,
        source = "${this.id}.${this.mimeType}",
        chapters = convert(this.chapters),
        connectedPostDto = this
    )
}
