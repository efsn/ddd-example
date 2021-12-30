package cn.example.ddd.user.infrastructure.repository

import cn.example.ddd.core.repository.R2dbcRepositoryBaseTest
import cn.example.ddd.user.infrastructure.repository.po.UserPO
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.OffsetDateTime

internal class UserR2dbcRepositoryTest : R2dbcRepositoryBaseTest() {

    @Autowired
    private lateinit var userR2dbcRepository: UserR2dbcRepository

    @Test
    fun `should save user repository successfully`() = runBlocking {
        val po = UserPO(username = "xx", password = "123", createDatetime = OffsetDateTime.now(), updateDatetime = OffsetDateTime.now())
        with(userR2dbcRepository.save(po)) {
            assertEquals(1, id)
            assertEquals("xx", username)
            assertEquals("123", password)
        }
    }
}
