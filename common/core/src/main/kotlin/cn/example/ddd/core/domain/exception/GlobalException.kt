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

const val UNKNOWN_ERROR_CODE = "99999"
const val AUTHENTICATION_ERROR_CODE = "88888"
const val SIGNATURE_INVALID = "77777"

class UnknownError : BizException(UNKNOWN_ERROR_CODE)
class UnExpectedException(override val cause: Throwable) : BizException(UNKNOWN_ERROR_CODE)
class SignatureInvalidException : BizException(SIGNATURE_INVALID)
