package com.napoleontest.presentation.main.view

import com.napoleontest.domain.model.Banner
import com.napoleontest.domain.model.Offer
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MainView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun displayOffers(offers: List<Offer>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun displayBanners(banners: List<Banner>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading(show : Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showMessage(message : Int)

}