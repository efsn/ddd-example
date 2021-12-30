package cn.example.ddd.user.infrastructure.repository

import cn.example.ddd.user.domain.model.entity.User
import cn.example.ddd.user.domain.model.entity.toPO
import cn.example.ddd.user.domain.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val userR2DbcRepository: UserR2dbcRepository
) : UserRepository {
    override suspend fun store(user: User) = userR2DbcRepository.save(user.toPO()).toEntity()
}
