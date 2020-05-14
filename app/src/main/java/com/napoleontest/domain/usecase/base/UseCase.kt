package com.napoleontest.domain.usecase.base

import android.util.Log
import com.napoleontest.domain.exception.ApiErrorHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

abstract class UseCase<Type, in Params>(private val apiErrorHandler: ApiErrorHandler) where Type : Any {

    abstract suspend fun run(params: Params? = null): Type

    fun invoke(
        params: Params?,
        onResult: (UseCaseResponse<Type>)?
    ) {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = run(params)
                onResult?.onSuccess(result)
                Log.d(TAG, "Response: $result")
            } catch (e: CancellationException) {
                Log.e(TAG, "Error: $e")
                onResult?.onError(apiErrorHandler.traceErrorException(e))
            } catch (e: Exception) {
                Log.e(TAG, "Error: $e cause: ${e.cause}")
                onResult?.onError(apiErrorHandler.traceErrorException(e))
            }
        }
    }

    companion object {
        private val TAG = UseCase::class.java.name
    }

}