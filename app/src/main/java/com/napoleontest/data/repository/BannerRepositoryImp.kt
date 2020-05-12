package com.napoleontest.data.repository

import com.napoleontest.data.network.BannerApi
import com.napoleontest.data.network.OfferApi
import com.napoleontest.domain.model.Banner
import com.napoleontest.domain.model.Offer
import com.napoleontest.domain.repository.BannerRepository
import com.napoleontest.domain.repository.OfferRepository

class BannerRepositoryImp(private val api: BannerApi) : BannerRepository {

    override suspend fun getBanners(): List<Banner> {
        return api.getBanners()
    }

}