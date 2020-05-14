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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val banners = ArrayList<Banner>()
        banners.add(Banner("id1", "Title1", "Desc1", "img1"))
        banners.add(Banner("id2", "Title2", "Desc2", "img2"))
        banners.add(Banner("id3", "Title3", "Desc3", "img3"))

        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        val vpAdapter = ViewPagerAdapter(banners)
        with(viewPager) {
            clipToPadding = false;
            setPadding(
                Util.convertDpToPixel(50, context),
                0,
                Util.convertDpToPixel(50, context),
                0
            );
            adapter = vpAdapter
        }

    }


}