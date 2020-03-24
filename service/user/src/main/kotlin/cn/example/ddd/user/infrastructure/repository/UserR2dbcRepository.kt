package cn.example.ddd.user.infrastructure.repository

import cn.example.ddd.core.repository.AbstractR2dbcRepository
import cn.example.ddd.user.infrastructure.repository.po.UserPO
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.asType
import org.springframework.data.r2dbc.core.awaitOne
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.ReactiveTransactionManager
import org.springframework.transaction.reactive.executeAndAwait

@Repository
class UserR2dbcRepository(
    private val dbClient: DatabaseClient,
    txManager: ReactiveTransactionManager
) : AbstractR2dbcRepository(txManager) {

    suspend fun save(user: UserPO): UserPO = executeAndAwait {
        dbClient.execute(
            """
            insert into users.user(username, password)
            values(:username, :password)
            returning *
        """.trimIndent()
        )
            .bind("username", user.username)
            .bind("password", user.password)
            .asType<UserPO>()
            .fetch()
            .awaitOne()
    }!!
}

interface UserReactiveRepository : ReactiveCrudRepository<UserPO, Long>