package com.jh.holaflytest.domain.exception

open class DomainException(override val message: String = "") : Throwable(message)
object BadRequestException: DomainException()
object NotFoundException: DomainException()
object UnauthorizedException: DomainException()
object TimeoutException: DomainException()
object InternalErrorException: DomainException()
object UnknownError: DomainException()
data class HttpErrorCode(val code: Int, override val message: String) : DomainException()
