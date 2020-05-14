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
import com.napoleontest.domain.model.Offer
import com.napoleontest.presentation.main.OfferAdapter
import com.napoleontest.presentation.main.RecyclerViewContainer
import com.napoleontest.presentation.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moxy.MvpAppCompatActivity
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

@ExperimentalCoroutinesApi
class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView, KodeinAware {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override val kodein by lazy { (applicationContext as App).kodein }
    private val presenter: MainPresenter by instance<MainPresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setData()
        setRecyclerView()

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)

        iv_info.setOnClickListener {
            showModal()
        }

        btn_top10.setOnClickListener { setBntSelected(btn_top10) }
        btn_shops.setOnClickListener { setBntSelected(btn_shops) }
        btn_products.setOnClickListener { setBntSelected(btn_products) }

        setBntSelected(btn_top10)

        if (fragment_container != null) {
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, BannerFragment())
            transaction.commit()
        }
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
        TODO("Not yet implemented")
    }

    private fun setData() { // please use loop statement for your dataset, this is just a sample of how to manage very simple data.
        val offer1 = Offer(
            "id1",
            "Title1",
            "desc1",
            "https://www.howtogeek.com/wp-content/uploads/2018/06/shutterstock_1006988770.png",
            "Акции",
            "product",
            0.0f,
            0.0f
        )
        val offer2 = Offer(
            "id2",
            "Title2",
            "desc2",
            "https://www.howtogeek.com/wp-content/uploads/2018/06/shutterstock_1006988770.png",
            "Акции",
            "product",
            0.0f,
            0.0f
        )
        val offer3 = Offer(
            "id3",
            "Title3",
            "desc3",
            "https://www.howtogeek.com/wp-content/uploads/2018/06/shutterstock_1006988770.png",
            "Скидки",
            "item",
            0.0f,
            0.0f
        )
        val offer4 = Offer(
            "id4",
            "Title4",
            "desc4",
            "https://www.howtogeek.com/wp-content/uploads/2018/06/shutterstock_1006988770.png",
            "Распродажа",
            "item",
            0.0f,
            0.0f
        )

        val item1 = RecyclerViewContainer(null, true, "Акции")
        val item2 = RecyclerViewContainer(offer1, false, null)
        val item3 = RecyclerViewContainer(offer2, false, null)
        val item4 = RecyclerViewContainer(null, true, "Скидки")
        val item5 = RecyclerViewContainer(offer3, false, null)
        val item6 = RecyclerViewContainer(null, true, "Распродажа")
        val item7 = RecyclerViewContainer(offer4, false, null)

        itemList.add(item1)
        itemList.add(item2)
        itemList.add(item3)
        itemList.add(item4)
        itemList.add(item5)
        itemList.add(item6)
        itemList.add(item7)
    }

    private var itemList = mutableListOf<RecyclerViewContainer>()

    private fun setRecyclerView() {

        val viewManager = LinearLayoutManager(this)
        val myAdapter = OfferAdapter(itemList)

        val decorator = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)

        val recyclerView = recycler_view.apply {
            layoutManager = viewManager
            adapter = myAdapter
            addItemDecoration(decorator)
            setHasFixedSize(true)
        }

    }

}