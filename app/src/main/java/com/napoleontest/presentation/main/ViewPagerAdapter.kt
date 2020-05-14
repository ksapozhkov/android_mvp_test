package com.napoleontest.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.napoleontest.databinding.ItemBannerBinding
import com.napoleontest.domain.model.Banner
import com.napoleontest.util.Util
import kotlin.properties.Delegates

class ViewPagerAdapter : PagerAdapter() {

    var mBannerList: List<Banner> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return mBannerList.size
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
        val banner = mBannerList[position]
        binding.tvBannerTitle.text = banner.title
        binding.tvBannerDesc.text = banner.desc
        binding.layoutInfo.visibility =
            if ((banner.title != null && banner.title.isNotEmpty()) || (banner.desc != null && banner.desc.isNotEmpty())
            ) View.VISIBLE else View.GONE
        Util.displayImage(container.context, banner.image, binding.ivBannerImg)

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