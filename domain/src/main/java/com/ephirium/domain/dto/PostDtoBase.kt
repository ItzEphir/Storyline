package com.ephirium.domain.dto

interface PostDtoBase : ItemDto {
    override var id: String
    var name: String
    var description: String
    var author: String
    var chapters: List<String>
}