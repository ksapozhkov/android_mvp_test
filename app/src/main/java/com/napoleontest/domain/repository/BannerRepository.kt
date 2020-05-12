package com.napoleontest.domain.repository

import com.napoleontest.domain.model.Banner

interface BannerRepository {

    suspend fun getBanners(): List<Banner>

}