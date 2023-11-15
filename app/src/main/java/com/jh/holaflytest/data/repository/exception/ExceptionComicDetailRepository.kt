package com.jh.holaflytest.data.repository.exception

import com.jh.holaflytest.data.exception.HttpErrors
import com.jh.holaflytest.domain.exception.CommonErrors
import com.jh.holaflytest.domain.exception.DomainException
import com.jh.holaflytest.domain.repository.DomainExceptionRepository
import retrofit2.HttpException

/**
 * Created by Jorge Henao on 15/11/23.
 */
class ExceptionComicDetailRepository: CommonErrors(), DomainExceptionRepository {

    override fun manageError(error: Throwable): DomainException {
        return if (error is HttpException) {
            HttpErrors.getHttpError(error)
        } else {
            manageException(error)
        }
    }
}
