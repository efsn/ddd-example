package cn.example.ddd.core.domain.exception

import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

class RequestFailedException(
    cause: BizException,
    private val responseStatus: HttpStatus,
    private val responseBody: String
) : BizException(
    cause.errorCode,
    cause.attributes,
    cause,
    cause.httpStatus,
    cause.data
) {
    override val message: String?
        get() = "${cause?.message} \n FailedResponse $responseStatus, body: $responseBody"
}

fun ResponseSpec.onStatusThrow(
    statusPredicate: (HttpStatus) -> Boolean,
    exceptionGenerator: (ClientResponse) -> Mono<BizException>
): ResponseSpec = this.onStatus({ statusPredicate(it) }) { response ->
    response.bodyToMono(String::class.java).defaultIfEmpty("").flatMap { rawBody ->
        val copiedResponse =
            ClientResponse
                .create(response.statusCode())
                .headers { it.addAll(response.headers().asHttpHeaders()) }
                .body(rawBody)
                .build()

        exceptionGenerator(copiedResponse).flatMap { Mono.error<BizException>(it) }.onErrorResume {
            when (it) {
                is BizException -> RequestFailedException(it, response.statusCode(), rawBody).toMono()
                else -> UnExpectedException(it).toMono()
            }
        }
    }
}

class WebClientException(message: String) : RuntimeException(message)

suspend fun ClientResponse.toWebClientException(responseBody: Any? = null): WebClientException {
    val exception = this.createException().awaitFirst()
    val body = responseBody?.toString() ?: exception.responseBodyAsString
    return WebClientException("\n\t${exception.message}, \n\tresponse: $body")
}
