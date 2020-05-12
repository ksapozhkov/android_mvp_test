package com.napoleontest.presentation.main.presenter

import com.napoleontest.presentation.base.MVPPresenter
import com.napoleontest.presentation.main.view.MainMVPView

interface MainMVPPresenter<V : MainMVPView> : MVPPresenter<V> {

    fun refreshOfferList()

}