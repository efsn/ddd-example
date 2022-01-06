package cn.example.ddd.core.domain.exception

const val UNKNOWN_ERROR_CODE = "99999"
const val AUTHENTICATION_ERROR_CODE = "88888"
const val SIGNATURE_INVALID = "77777"

class UnknownError : BizException(UNKNOWN_ERROR_CODE)
class UnExpectedException(override val cause: Throwable) : BizException(UNKNOWN_ERROR_CODE)
class SignatureInvalidException : BizException(SIGNATURE_INVALID)
