package cn.example.ddd.user.domain.model.entity

import cn.example.ddd.core.domain.vo.toOffset
import cn.example.ddd.user.infrastructure.repository.po.UserPO
import java.time.LocalDateTime

data class User(
    val id: Long? = null,
    val username: String,
    val password: String,
    val createDateTime: LocalDateTime = LocalDateTime.now(),
    val updateDateTime: LocalDateTime = createDateTime
)

fun User.toPO() = UserPO(id, username, password, createDateTime.toOffset(), updateDateTime.toOffset())
