package cn.example.ddd.user.application

import cn.example.ddd.user.application.command.UserCreateCommand
import cn.example.ddd.user.application.command.toEntity
import cn.example.ddd.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserApplicationService(
    private val repository: UserRepository
) {
    suspend fun add(command: UserCreateCommand) = repository.store(command.toEntity())
}
