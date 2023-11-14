package com.jh.holaflytest.domain.repository

import com.jh.holaflytest.domain.exception.DomainException

interface DomainExceptionRepository {

    fun manageError(error: Throwable): DomainException
}
