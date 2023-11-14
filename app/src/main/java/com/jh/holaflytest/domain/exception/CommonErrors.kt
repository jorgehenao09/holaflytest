package com.jh.holaflytest.domain.exception

import java.net.ConnectException
import java.net.SocketTimeoutException

open class CommonErrors {

    fun manageException(error: Throwable) = manageErrors(error)

    private fun manageErrors(error: Throwable): DomainException {
        return when (error) {
            is SocketTimeoutException -> TimeoutException
            is ConnectException -> InternalErrorException
            else -> UnknownError
        }
    }
}
