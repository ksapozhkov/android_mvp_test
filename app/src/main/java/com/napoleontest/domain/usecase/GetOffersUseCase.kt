package com.napoleontest.domain.usecase

import com.napoleontest.domain.exception.ApiErrorHandler
import com.napoleontest.domain.model.Offer
import com.napoleontest.domain.repository.OfferRepository
import com.napoleontest.domain.usecase.base.UseCase

class GetOffersUseCase constructor(
    private val offersRepository: OfferRepository,
    apiErrorHandler: ApiErrorHandler
) : UseCase<List<Offer>, Any?>(apiErrorHandler) {

    override suspend fun run(params: Any?): List<Offer> {
        return offersRepository.getOffers()
    }

}