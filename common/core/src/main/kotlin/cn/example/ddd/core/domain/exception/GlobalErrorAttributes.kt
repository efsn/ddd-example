/*
* Copyright (C) 2022 Bug.S.C Chen (efsn.chan@gmail.com).
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      https://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package cn.example.ddd.core.domain.exception

import cn.example.ddd.core.configuration.LocaleService
import org.slf4j.LoggerFactory
import org.springframework.boot.web.error.ErrorAttributeOptions
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

    override fun getErrorAttributes(request: ServerRequest, options: ErrorAttributeOptions): Map<String, Any> =
        when (val error = getError(request)) {
            is BizException -> {
                buildErrorCode(error.errorCode, error.attributes)
            }
            is WebExchangeBindException -> {
                buildErrorCode(error.allErrors[0]?.defaultMessage ?: UNKNOWN_ERROR_CODE)
            }
            is ResponseStatusException -> {
                if (error.status == HttpStatus.NOT_FOUND) {
                    logger.warn(error.message, error)
                } else {
                    logger.error(error.message, error)
                }
                super.getErrorAttributes(request, options)
            }
            else -> {
                logger.error(error.message, error)
                super.getErrorAttributes(request, options)
            }
        }

    private fun buildErrorCode(errorCode: String, attributes: List<String> = emptyList()): Map<String, Any> = mapOf(
        "errorCode" to errorCode,
        "message" to localeService.getMessage(errorCode, *(attributes.toTypedArray()))
    )
}
