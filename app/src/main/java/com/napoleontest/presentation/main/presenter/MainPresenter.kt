package com.napoleontest.presentation.main.presenter

import com.napoleontest.App
import com.napoleontest.domain.model.Offer
import com.napoleontest.domain.usecase.GetOffersUseCase
import com.napoleontest.domain.usecase.base.UseCaseResponse
import com.napoleontest.presentation.main.view.MainView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moxy.MvpPresenter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainPresenter : MvpPresenter<MainView>(), KodeinAware {

    override val kodein: Kodein by kodein(App.instance)
    private val getOffersUserCase: GetOffersUseCase by instance<GetOffersUseCase>()

    @ExperimentalCoroutinesApi
    override fun onFirstViewAttach() {
        refreshOfferList()
    }

    @ExperimentalCoroutinesApi
    fun refreshOfferList() {
        getOffersUserCase.invoke(null, object : UseCaseResponse<List<Offer>> {
            override fun onSuccess(result: List<Offer>) {
                viewState.displayOffers(result)
                TODO("Not yet implemented")
            }

            override fun onError(errorModel: com.napoleontest.domain.model.Error?) {
                TODO("Not yet implemented")
            }
        })
    }

}