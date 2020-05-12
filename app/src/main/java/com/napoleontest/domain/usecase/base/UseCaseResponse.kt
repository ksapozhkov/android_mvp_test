package com.napoleontest.domain.usecase.base

import com.napoleontest.domain.model.Error

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(errorModel: Error?)
}

