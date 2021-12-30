package cn.example.ddd.user.representation.response

import cn.example.ddd.user.domain.model.entity.User

data class UserCreateResponse(
    val id: Long?
) {
    constructor(entity: User) : this(entity.id)
}
