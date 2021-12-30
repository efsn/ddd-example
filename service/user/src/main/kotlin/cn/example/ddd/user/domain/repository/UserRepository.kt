package cn.example.ddd.user.domain.repository

import cn.example.ddd.user.domain.model.entity.User

interface UserRepository {
    suspend fun store(user: User): User
}
