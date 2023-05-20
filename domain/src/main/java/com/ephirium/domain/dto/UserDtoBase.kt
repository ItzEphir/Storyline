package com.ephirium.domain.dto

interface UserDtoBase : ItemDto {
    override var id: String
    var login: String
    var email: String
    var displayName: String
    var description: String
    var posts: List<String>
}