package com.napoleontest.presentation.main.view

import android.app.AlertDialog
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.napoleontest.App
import com.napoleontest.R
import com.napoleontest.domain.model.Banner
import com.napoleontest.domain.model.Offer
import com.napoleontest.presentation.main.OfferAdapter
import com.napoleontest.presentation.main.OfferViewContainer
import com.napoleontest.presentation.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import org.kodein.di.KodeinAware

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView, KodeinAware {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    private lateinit var mOfferAdapter: OfferAdapter
    override val kodein by lazy { (applicationContext as App).kodein }

    private val presenter by moxyPresenter { MainPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mOfferAdapter = OfferAdapter()

        val decorator = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mOfferAdapter
            addItemDecoration(decorator)
            setHasFixedSize(true)
        }

        with(searchView) {
            setSearchableInfo(
                (getSystemService(Context.SEARCH_SERVICE) as SearchManager).getSearchableInfo(
                    componentName
                )
            )
            setIconifiedByDefault(false)
        }

        iv_info.setOnClickListener {
            showModal()
        }

        btn_top10.setOnClickListener { setBntSelected(btn_top10) }
        btn_shops.setOnClickListener { setBntSelected(btn_shops) }
        btn_products.setOnClickListener { setBntSelected(btn_products) }

        setBntSelected(btn_top10)

        if (fragment_container != null) {
            if (savedInstanceState != null) {
                return;
            }
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, BannerFragment())
            transaction.commit()
        }

//        presenter.getOffers()
    }

    private fun setBntSelected(btn: Button) {
        when {
            btn_top10.id == btn.id -> {
                btn_top10.isSelected = true
                btn_shops.isSelected = false
                btn_products.isSelected = false
            }
            btn_shops.id == btn.id -> {
                btn_top10.isSelected = false
                btn_shops.isSelected = true
                btn_products.isSelected = false
            }
            btn_products.id == btn.id -> {
                btn_top10.isSelected = false
                btn_shops.isSelected = false
                btn_products.isSelected = true
            }
        }
    }

    private fun showModal() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage("Текст")
            .setTitle("Сообщение")
            .setPositiveButton("Ok") { _, _ -> }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun displayOffers(offers: List<Offer>) {
        val itemList = mutableListOf<OfferViewContainer>()
        val groupNames = ArrayList<String>()
        for (i in offers.indices) {
            val offer = offers[i]
            if (!groupNames.contains(offer.groupName)) {
                groupNames.add(offer.groupName)
                itemList.add(OfferViewContainer(null, true, offer.groupName))
                for (j in i until offers.size) {
                    val compareOffer = offers[j]
                    if (compareOffer.groupName == offer.groupName)
                        itemList.add(OfferViewContainer(compareOffer, false, null))
                }
            }
        }
        mOfferAdapter.mOfferList = itemList
    }

    override fun displayBanners(banners: List<Banner>) {
        println(banners.size)
        TODO("Not yet implemented")
    }

}