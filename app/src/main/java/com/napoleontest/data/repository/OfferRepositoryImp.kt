package com.napoleontest.data.repository

import com.napoleontest.data.network.OfferApi
import com.napoleontest.domain.model.Offer
import com.napoleontest.domain.repository.OfferRepository

class OfferRepositoryImp(private val api: OfferApi) : OfferRepository {

    override suspend fun getOffers(): List<Offer> {
        return api.getOffers()
    }

}