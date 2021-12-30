package cn.example.ddd.user.application

import cn.example.ddd.user.application.command.UserCreateCommand
import cn.example.ddd.user.domain.model.entity.User
import cn.example.ddd.user.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class UserApplicationServiceTest {

    private val repository = mockk<UserRepository>()
    private val service = UserApplicationService(repository)

    @Test
    fun `should add new user successfully`() = runBlocking {
        val user = User(1, "xx", "123")
        val command = UserCreateCommand("xx", "123")

        // given
        coEvery { repository.store(any()) } returns user

        with(service.add(command)) {
            assertEquals(1, id)
            assertEquals("xx", username)
            assertEquals("123", password)
        }
    }
}
