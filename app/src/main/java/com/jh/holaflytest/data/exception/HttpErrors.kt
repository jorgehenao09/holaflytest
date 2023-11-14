package com.jh.holaflytest.data.exception

import com.google.gson.Gson
import com.jh.holaflytest.domain.exception.BadRequestException
import com.jh.holaflytest.domain.exception.DomainException
import com.jh.holaflytest.domain.exception.HttpErrorCode
import com.jh.holaflytest.domain.exception.NotFoundException
import com.jh.holaflytest.domain.exception.UnauthorizedException
import retrofit2.HttpException
import javax.net.ssl.HttpsURLConnection

object HttpErrors {

    private val gson = Gson()

    private val httpErrors = mapOf(
        HttpsURLConnection.HTTP_BAD_REQUEST to BadRequestException,
        HttpsURLConnection.HTTP_NOT_FOUND to NotFoundException,
        HttpsURLConnection.HTTP_UNAUTHORIZED to UnauthorizedException,
    )

    fun getHttpError(error: HttpException): DomainException {
        return if (httpErrors.containsKey(error.code())) {
            httpErrors.getValue(error.code())
        } else {
            HttpErrorCode(error.code(), getMessage(error).message)
        }
    }

    private fun getMessage(exception: HttpException): DomainException {
        return try {
            var jsonString = exception.response()?.errorBody()?.string()
            if (jsonString.isNullOrEmpty()) jsonString = JSON_EMPTY
            gson.fromJson(jsonString, DomainException::class.java)
        } catch (exception: Exception) {
            DomainException(JSON_EMPTY)
        }
    }
}

private const val JSON_EMPTY = "{}"
