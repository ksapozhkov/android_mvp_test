package com.napoleontest.presentation.main.view

import com.napoleontest.domain.model.Offer
import com.napoleontest.presentation.base.MVPView

interface MainMVPView : MVPView {

    fun displayOffers(offers: List<Offer>)
    fun openInfo()
}