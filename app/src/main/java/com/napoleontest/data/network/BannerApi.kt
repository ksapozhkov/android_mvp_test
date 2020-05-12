package com.napoleontest.data.network

import com.napoleontest.domain.model.Banner
import retrofit2.http.GET

interface BannerApi {

    @GET("banners.json")
    suspend fun getBanners(): List<Banner>

}