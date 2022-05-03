/*
* Copyright (C) 2022 Arthur Chen (efsn.chan@gmail.com).
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

import org.springframework.http.HttpStatus

abstract class BizException(
    val errorCode: String,
    val attributes: List<String> = emptyList(),
    override val cause: Throwable? = null,
    val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
    open val data: Map<String, Any> = emptyMap(),
    val description: String? = ""
) : RuntimeException(errorCode)
