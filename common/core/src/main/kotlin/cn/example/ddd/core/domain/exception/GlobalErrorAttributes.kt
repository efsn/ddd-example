package cn.example.ddd.core.domain.exception

import cn.example.ddd.core.configuration.LocaleService
import java.text.MessageFormat
import org.slf4j.LoggerFactory
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.server.ResponseStatusException

@Component
class GlobalErrorAttributes(
    private val localeService: LocaleService
) : DefaultErrorAttributes() {

    private val logger = LoggerFactory.getLogger(GlobalErrorAttributes::class.java)

    override fun getErrorAttributes(request: ServerRequest, includeStackTrace: Boolean): Map<String, Any> {
        return when (val error = getError(request)) {
            is BizException -> {
                buildErrorCode(error.errorCode, request, error.attributes)
            }
            is WebExchangeBindException -> {
                buildErrorCode(error.allErrors[0]?.defaultMessage ?: UNKNOWN_ERROR_CODE, request)
            }
            is ResponseStatusException -> {
                if (error.status == HttpStatus.NOT_FOUND) {
                    logger.warn(error.message, error)
                } else {
                    logger.error(error.message, error)
                }
                super.getErrorAttributes(request, includeStackTrace)
            }
            else -> {
                logger.error(error.message, error)
                super.getErrorAttributes(request, includeStackTrace)
            }
        }
    }

    private fun buildErrorCode(
        errorCode: String,
        request: ServerRequest,
        attributes: List<String> = emptyList()
    ): Map<String, Any> = mapOf(
        "errorCode" to errorCode,
        "message" to MessageFormat.format(localeService.getMessage(errorCode, request.exchange()), *(attributes.toTypedArray()))
    )
}