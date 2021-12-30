package cn.example.ddd.core.domain.exception

import org.springframework.http.HttpStatus

abstract class BizException(
    val errorCode: String,
    val attributes: List<String> = emptyList(),
    override val cause: Throwable? = null,
    val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
    open val data: Map<String, Any> = emptyMap(),
    val description: String? = ""
) : RuntimeException(errorCode)
