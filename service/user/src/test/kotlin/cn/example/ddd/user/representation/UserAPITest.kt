package cn.example.ddd.user.representation

import cn.example.ddd.core.CoroutineTestSupport
import cn.example.ddd.core.representation.BaseAPITest
import cn.example.ddd.user.application.UserApplicationService
import cn.example.ddd.user.domain.model.entity.User
import cn.example.ddd.user.representation.request.UserCreateRequest
import cn.example.ddd.user.representation.request.toCommand
import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType.APPLICATION_JSON

@Import(UserAPI::class)
@ExperimentalCoroutinesApi
internal class UserAPITest : BaseAPITest(), CoroutineTestSupport by CoroutineTestSupport.Delegator {
    @MockkBean
    private lateinit var service: UserApplicationService

    @Test
    fun `should return 200 when add new user`() = runTest {
        val request = UserCreateRequest("xx", "123")
        val command = request.toCommand()
        val user = User(1, "xx", "123")

        coEvery { service.add(command) } returns user

        webClient
            .post()
            .uri("/users")
            .contentType(APPLICATION_JSON)
            .bodyValue(
                """
                {
                  "username": "xx",
                  "password": "123"
                }
                """.trimIndent()
            )
            .exchange()
            .expectStatus().isOk
    }
}
