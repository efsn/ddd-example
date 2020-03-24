package cn.example.ddd.user.infrastructure.repository.po

import cn.example.ddd.user.domain.model.entity.User
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("user")
data class UserPO(
    @field:Id
    val id: Long? = null,
    val username: String,
    val password: String
)

fun UserPO.toEntity() = User(id, username, password)