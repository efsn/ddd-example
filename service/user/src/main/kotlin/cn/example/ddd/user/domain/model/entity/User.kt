package cn.example.ddd.user.domain.model.entity

import cn.example.ddd.user.infrastructure.repository.po.UserPO

data class User(
    val id: Long? = null,
    val username: String,
    val password: String
)

fun User.toPO() = UserPO(id, username, password)