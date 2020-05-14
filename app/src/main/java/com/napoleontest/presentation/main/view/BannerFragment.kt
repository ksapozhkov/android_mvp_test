package com.napoleontest.presentation.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.napoleontest.R
import com.napoleontest.domain.model.Banner
import com.napoleontest.presentation.main.ViewPagerAdapter
import com.napoleontest.util.SlideAnimation
import com.napoleontest.util.Util
import kotlinx.android.synthetic.main.fragment_banner.*


class BannerFragment : Fragment() {

    private lateinit var mAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        mAdapter = ViewPagerAdapter()

        val pagerListener: OnPageChangeListener = object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position >= mAdapter.count - 3)
                    (activity as MainActivity).presenter.getBanners()
            }

            override fun onPageSelected(i: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        }
        with(viewPager) {
            clipToPadding = false;
            setPadding(
                Util.convertDpToPixel(50, context),
                0,
                Util.convertDpToPixel(50, context),
                0
            );
            addOnPageChangeListener(pagerListener)
            adapter = mAdapter
        }
    }

    fun setData(banners: List<Banner>) {
        if (mAdapter.count == 0 && banners.isNotEmpty())
            SlideAnimation(viewPager, 0, Util.convertDpToPixel(190, context!!), 500).start()
        mAdapter.addItems(banners)
    }

}