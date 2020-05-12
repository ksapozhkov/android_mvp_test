package com.napoleontest.presentation.main.presenter

import com.napoleontest.App
import com.napoleontest.domain.model.Offer
import com.napoleontest.domain.usecase.GetOffersUseCase
import com.napoleontest.domain.usecase.base.UseCaseResponse
import com.napoleontest.presentation.base.BasePresenter
import com.napoleontest.presentation.main.view.MainMVPView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainPresenter<V : MainMVPView> : BasePresenter<V>(), MainMVPPresenter<V>, KodeinAware {

    override val kodein: Kodein by kodein(App.instance)
    private val getOffersUserCase: GetOffersUseCase by instance<GetOffersUseCase>()

    override fun onAttach(view: V?) {
        super.onAttach(view)
    }

    @ExperimentalCoroutinesApi
    override fun refreshOfferList() {
        getOffersUserCase.invoke(null, object : UseCaseResponse<List<Offer>> {
            override fun onSuccess(result: List<Offer>) {
                getView()?.displayOffers(result)
                TODO("Not yet implemented")
            }

            override fun onError(errorModel: com.napoleontest.domain.model.Error?) {
                TODO("Not yet implemented")
            }
        })
    }

}