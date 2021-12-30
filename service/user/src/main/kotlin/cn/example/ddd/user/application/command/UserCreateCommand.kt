package cn.example.ddd.user.application.command

import cn.example.ddd.user.domain.model.entity.User

data class UserCreateCommand(
    val username: String,
    val password: String
)

fun UserCreateCommand.toEntity() = User(username = username, password = password)
