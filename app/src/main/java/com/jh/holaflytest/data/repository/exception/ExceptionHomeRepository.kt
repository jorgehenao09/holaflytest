package com.jh.holaflytest.data.repository.exception

import com.jh.holaflytest.data.exception.HttpErrors.getHttpError
import com.jh.holaflytest.domain.exception.CommonErrors
import com.jh.holaflytest.domain.exception.DomainException
import com.jh.holaflytest.domain.repository.DomainExceptionRepository
import retrofit2.HttpException

class ExceptionHomeRepository: CommonErrors(), DomainExceptionRepository {

    override fun manageError(error: Throwable): DomainException {
        return if (error is HttpException) {
            getHttpError(error)
        } else {
            manageException(error)
        }
    }
}
