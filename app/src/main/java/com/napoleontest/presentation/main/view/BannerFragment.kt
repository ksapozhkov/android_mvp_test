package com.napoleontest.presentation.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.napoleontest.R
import com.napoleontest.domain.model.Banner
import com.napoleontest.presentation.main.ViewPagerAdapter
import com.napoleontest.util.Util

class BannerFragment : Fragment() {

    private lateinit var mAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        mAdapter = ViewPagerAdapter()
        with(viewPager) {
            clipToPadding = false;
            setPadding(
                Util.convertDpToPixel(50, context),
                0,
                Util.convertDpToPixel(50, context),
                0
            );
            adapter = mAdapter
        }

    }

    fun setData(banners: List<Banner>) {
        mAdapter.mBannerList = banners
    }

}