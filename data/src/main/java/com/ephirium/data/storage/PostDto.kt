package com.ephirium.data.storage

import com.ephirium.domain.dto.PostDtoBase
import com.google.firebase.firestore.DocumentId

data class PostDto(
    @DocumentId override var id: String = "",
    override var name: String = "",
    override var description: String = "",
    override var author: String = "",
    override var chapters: List<String> = ArrayList(),
    override var mimeType: String = "",
) : PostDtoBase
