package com.napoleontest.domain.repository

import com.napoleontest.domain.model.Offer

interface OfferRepository {

    suspend fun getOffers(): List<Offer>

}