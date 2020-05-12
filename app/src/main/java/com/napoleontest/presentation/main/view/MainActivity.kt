package com.napoleontest.presentation.main.view

import android.os.Bundle
import com.napoleontest.R
import com.napoleontest.domain.model.Offer
import com.napoleontest.presentation.base.BaseActivity
import com.napoleontest.presentation.main.presenter.MainPresenter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.newInstance

@ExperimentalCoroutinesApi
class MainActivity : BaseActivity(), MainMVPView, KodeinAware {

    override val kodein: Kodein by kodein()
    private val presenter by kodein.newInstance { MainPresenter<MainMVPView>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)

        presenter.refreshOfferList()
    }

    override fun displayOffers(offers: List<Offer>) {
        TODO("Not yet implemented")
    }

    override fun openInfo() {
        TODO("Not yet implemented")
    }

}