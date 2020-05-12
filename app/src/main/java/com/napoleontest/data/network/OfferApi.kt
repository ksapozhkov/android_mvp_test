package com.napoleontest.data.network

import com.napoleontest.domain.model.Offer
import retrofit2.http.GET

interface OfferApi {

    @GET("offers.json")
    suspend fun getOffers(): List<Offer>

}