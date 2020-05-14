package com.napoleontest.presentation.main.presenter

import com.napoleontest.App
import com.napoleontest.domain.model.Banner
import com.napoleontest.domain.model.Offer
import com.napoleontest.domain.usecase.GetBannersUseCase
import com.napoleontest.domain.usecase.GetOffersUseCase
import com.napoleontest.domain.usecase.base.UseCaseResponse
import com.napoleontest.presentation.main.view.MainView
import moxy.InjectViewState
import moxy.MvpPresenter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

@InjectViewState
class MainPresenter : MvpPresenter<MainView>(), KodeinAware {

    override val kodein: Kodein by kodein(App.instance)
    private val getOffersUserCase: GetOffersUseCase by instance<GetOffersUseCase>()
    private val getBannerUserCase: GetBannersUseCase by instance<GetBannersUseCase>()

    override fun onFirstViewAttach() {
        getOffers()
        getBanners()
    }

    private fun getOffers() {
        getOffersUserCase.invoke(null, object : UseCaseResponse<List<Offer>> {
            override fun onSuccess(result: List<Offer>) {
                viewState.displayOffers(result)
            }

            override fun onError(errorModel: com.napoleontest.domain.model.Error?) {
                println(errorModel?.message)
            }
        })
    }

    fun getBanners() {
        getBannerUserCase.invoke(null, object : UseCaseResponse<List<Banner>> {
            override fun onSuccess(result: List<Banner>) {
                viewState.displayBanners(result)
            }

            override fun onError(errorModel: com.napoleontest.domain.model.Error?) {
                println(errorModel?.message)
            }
        })
    }

}