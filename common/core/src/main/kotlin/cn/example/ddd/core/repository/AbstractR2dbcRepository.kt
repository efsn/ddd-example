package cn.example.ddd.core.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.r2dbc.postgresql.codec.Json
import org.springframework.transaction.ReactiveTransactionManager
import org.springframework.transaction.reactive.TransactionalOperator

abstract class AbstractR2dbcRepository(
    private val transactionManager: ReactiveTransactionManager
) : TransactionalOperator by TransactionalOperator.create(transactionManager) {
    private val objectMapper: ObjectMapper = jacksonObjectMapper()
    protected fun Any.toR2dbcJson(): Json = Json.of(objectMapper.writeValueAsString(this))
}
