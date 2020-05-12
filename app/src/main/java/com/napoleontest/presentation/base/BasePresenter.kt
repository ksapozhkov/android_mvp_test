package com.napoleontest.presentation.base

abstract class BasePresenter<V : MVPView> () : MVPPresenter<V> {

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        view = null
    }

}