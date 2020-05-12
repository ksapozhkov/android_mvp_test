package com.napoleontest.domain.exception

import com.napoleontest.domain.model.Error
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * This class trace exceptions(api call or parse data or connection errors) &
 * depending on what exception returns [ErrorModel]
 *
 * */
class ApiErrorHandler {

    fun traceErrorException(throwable: Throwable?): Error {
       /* val errorModel: Error? = when (throwable) {

            // if throwable is an instance of HttpException
            // then attempt to parse error data from response body
            is HttpException -> {
                if (throwable.code() == 401) {
                    Error(throwable.message(), throwable.code())
                } else {
                    getHttpError(throwable.response()?.errorBody())
                }
            }

            // handle api call timeout error
            is SocketTimeoutException -> {
                com.napoleontest.domain.model.Error(
                    throwable.message,
                    ErrorModel.ErrorStatus.TIMEOUT
                )
            }

            // handle connection error
            is IOException -> {
                com.napoleontest.domain.model.Error(
                    throwable.message,
                    Error.ErrorStatus.NO_CONNECTION
                )
            }
            else -> null
        }*/
        return Error("Undefined Error", 0)
    }

    /**
     * attempts to parse http response body and get error data from it
     *
     * @param body retrofit response body
     * @return returns an instance of [ErrorModel] with parsed data or NOT_DEFINED status
     */
    /*private fun getHttpError(body: ResponseBody?): Error {
        return try {
            // use response body to get error detail
            Error(body.toString(), 400, Error.ErrorStatus.BAD_RESPONSE)
        } catch (e: Throwable) {
            e.printStackTrace()
            Error(message = e.message, errorStatus = Error.ErrorStatus.NOT_DEFINED)
        }

    }*/
}