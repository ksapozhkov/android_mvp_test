package com.napoleontest.presentation.main.presenter

import android.util.Log
import com.napoleontest.App
import com.napoleontest.R
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
    private val Tag = MainPresenter::class.java.canonicalName
    private val getOffersUserCase: GetOffersUseCase by instance<GetOffersUseCase>()
    private val getBannerUserCase: GetBannersUseCase by instance<GetBannersUseCase>()
    private var isOfferLoading = false
    private var isBannerLoading = false

    override fun onFirstViewAttach() {
        getOffers()
        getBanners()
    }

    private fun getOffers() {
        if (!isOfferLoading) {
            setOfferLoading(true)
            getOffersUserCase.invoke(null, object : UseCaseResponse<List<Offer>> {
                override fun onSuccess(result: List<Offer>) {
                    setOfferLoading(false)
                    viewState.displayOffers(result)
                }

                override fun onError(errorModel: com.napoleontest.domain.model.ErrorModel?) {
                    setOfferLoading(false)
                    Log.e(Tag, errorModel!!.getErrorMessage())
                    viewState.showMessage(R.string.unable_to_load_offers)
                }
            })
        }
    }

    fun getBanners() {
        if (!isBannerLoading) {
            isBannerLoading = true
            getBannerUserCase.invoke(null, object : UseCaseResponse<List<Banner>> {
                override fun onSuccess(result: List<Banner>) {
                    isBannerLoading = false
                    viewState.displayBanners(result)
                }

                override fun onError(errorModel: com.napoleontest.domain.model.ErrorModel?) {
                    isBannerLoading = false
                    Log.e(Tag, errorModel!!.getErrorMessage())
                    viewState.showMessage(R.string.unable_to_load_banners)
                }
            })
        }
    }

    private fun setOfferLoading(loading: Boolean) {
        isOfferLoading = loading
        viewState.showLoading(loading)
    }

}