package com.napoleontest.domain.model

/**
 * This class designed to show different types of errors through error status & message
 *
 * */
data class ErrorModel(
    val message: String?,
    val code: Int?,
    var errorStatus: ErrorStatus
) {
    constructor(message: String?, errorStatus: ErrorStatus) : this(message, null, errorStatus)

    fun getErrorMessage(): String {
        return when (errorStatus) {
            ErrorStatus.NO_CONNECTION -> Companion.NO_CONNECTION_ERROR_MESSAGE
            ErrorStatus.BAD_RESPONSE -> Companion.BAD_RESPONSE_ERROR_MESSAGE
            ErrorStatus.TIMEOUT -> Companion.TIME_OUT_ERROR_MESSAGE
            ErrorStatus.EMPTY_RESPONSE -> Companion.EMPTY_RESPONSE_ERROR_MESSAGE
            ErrorStatus.NOT_DEFINED -> Companion.NOT_DEFINED_ERROR_MESSAGE
            ErrorStatus.UNAUTHORIZED -> Companion.UNAUTHORIZED_ERROR_MESSAGE
        }
    }

    enum class ErrorStatus {
        /**
         * error in connecting to repository (Server or Database)
         */
        NO_CONNECTION,

        /**
         * error in getting value (Json Error, Server Error, etc)
         */
        BAD_RESPONSE,

        /**
         * Time out  error
         */
        TIMEOUT,

        /**
         * no data available in repository
         */
        EMPTY_RESPONSE,

        /**
         * an unexpected error
         */
        NOT_DEFINED,

        /**
         * bad credential
         */
        UNAUTHORIZED
    }

    companion object {
        private const val NO_CONNECTION_ERROR_MESSAGE = "No connection!"
        private const val BAD_RESPONSE_ERROR_MESSAGE = "Bad response!"
        private const val TIME_OUT_ERROR_MESSAGE = "Time out!"
        private const val EMPTY_RESPONSE_ERROR_MESSAGE = "Empty response!"
        private const val NOT_DEFINED_ERROR_MESSAGE = "Not defined!"
        private const val UNAUTHORIZED_ERROR_MESSAGE = "Unauthorized!"
    }

}