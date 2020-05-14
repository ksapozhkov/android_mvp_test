package com.napoleontest.util

import android.content.Context
import android.util.DisplayMetrics
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

class Util {

    companion object {
        fun convertDpToPixel(dp: Int, context: Context): Int {
            return (dp * (context.resources
                .displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
        }

        fun displayImage(
            context: Context,
            url: String,
            imageView: ImageView
        ) {
            var requestOptions = RequestOptions()
            requestOptions =
                requestOptions.transform(RoundedCorners(convertDpToPixel(8, context)))
            GlideApp.with(context).load(url)
                .apply(requestOptions)
                .into(imageView)
        }
    }

}

@GlideModule
class MyAppGlideModule : AppGlideModule()