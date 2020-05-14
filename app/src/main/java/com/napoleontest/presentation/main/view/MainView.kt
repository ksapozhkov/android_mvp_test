package com.napoleontest.presentation.main.view

import com.napoleontest.domain.model.Offer
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MainView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun displayOffers(offers: List<Offer>)

}