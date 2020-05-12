package com.napoleontest.domain.usecase

import com.napoleontest.domain.exception.ApiErrorHandler
import com.napoleontest.domain.model.Banner
import com.napoleontest.domain.repository.BannerRepository
import com.napoleontest.domain.usecase.base.UseCase

class GetBannersUseCase constructor(
    private val offersRepository: BannerRepository,
    apiErrorHandler: ApiErrorHandler
) : UseCase<List<Banner>, Any?>(apiErrorHandler) {

    override suspend fun run(params: Any?): List<Banner> {
        return offersRepository.getBanners()
    }

}