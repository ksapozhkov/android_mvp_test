package com.napoleontest.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.napoleontest.databinding.ItemBannerBinding
import com.napoleontest.domain.model.Banner

class ViewPagerAdapter(
    var banners: ArrayList<Banner>
) : PagerAdapter() {
    var inflater: LayoutInflater? = null

    override fun getCount(): Int {
        return banners.size
    }

    override fun isViewFromObject(
        view: View,
        obj: Any
    ): Boolean {
        return view === obj as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemBannerBinding.inflate(
            LayoutInflater.from(container.context),
            container,
            false
        )
        val banner = banners[position]
        binding.tvBannerTitle.text = banner.title
        binding.tvBannerDesc.text = banner.desc

        (container as ViewPager).addView(binding.root)
        return binding.root
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        obj: Any
    ) {
        (container as ViewPager).removeView(obj as LinearLayout)
    }

}