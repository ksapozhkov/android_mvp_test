package com.napoleontest.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ContentLoadingProgressBar

abstract class BaseActivity : AppCompatActivity(), MVPView {

    private var progressDialog: ContentLoadingProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun hideProgress() {
        progressDialog?.let { if (it.isShown) it.hide() }
    }

    override fun showProgress() {
        hideProgress()
        progressDialog?.let { if (!it.isShown) it.show() }
    }


}