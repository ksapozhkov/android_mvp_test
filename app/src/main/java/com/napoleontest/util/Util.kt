package com.napoleontest.util

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

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
                requestOptions.transform(CenterCrop(), RoundedCorners(convertDpToPixel(8, context)))
            GlideApp.with(context).load(url)
                .diskCacheStrategy(
                    DiskCacheStrategy.ALL
                )
                .apply(requestOptions)
                .into(imageView);
        }

    }

}

@GlideModule
class MyAppGlideModule : AppGlideModule()