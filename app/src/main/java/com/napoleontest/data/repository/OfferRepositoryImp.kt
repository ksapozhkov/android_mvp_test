package com.napoleontest.data.repository

import com.lifehacktestapp.android.data.db.OfferDao
import com.napoleontest.App
import com.napoleontest.data.network.OfferApi
import com.napoleontest.domain.model.Offer
import com.napoleontest.domain.repository.OfferRepository

class OfferRepositoryImp(private val api: OfferApi, private val dao: OfferDao) : OfferRepository {

    override suspend fun getOffers(): List<Offer> {
        if (App.isNetworkAvailable())
            dao.insertAll(api.getOffers())
        return dao.getAll()
    }

}