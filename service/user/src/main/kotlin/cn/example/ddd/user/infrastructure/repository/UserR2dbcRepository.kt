package cn.example.ddd.user.infrastructure.repository

import cn.example.ddd.core.repository.AbstractR2dbcRepository
import cn.example.ddd.user.infrastructure.repository.po.UserPO
import cn.example.ddd.user.infrastructure.repository.po.toUserPO
import io.r2dbc.spi.Row
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.awaitOne
import org.springframework.stereotype.Repository
import org.springframework.transaction.ReactiveTransactionManager
import org.springframework.transaction.reactive.executeAndAwait

@Repository
class UserR2dbcRepository(
    private val dbClient: DatabaseClient,
    txManager: ReactiveTransactionManager
) : AbstractR2dbcRepository(txManager) {

    suspend fun save(user: UserPO): UserPO = executeAndAwait {
        dbClient.sql(
            """
            insert into users.user(username, password, create_datetime, update_datetime)
            values(:username, :password, :createDatetime,:updateDatetime)
            returning *
            """.trimIndent()
        )
            .bind("username", user.username)
            .bind("password", user.password)
            .bind("createDatetime", user.createDatetime)
            .bind("updateDatetime", user.updateDatetime)
            .map(Row::toUserPO)
            .awaitOne()
    }!!
}

// interface UserReactiveRepository : ReactiveCrudRepository<UserPO, Long>
