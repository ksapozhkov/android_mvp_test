package com.napoleontest.util

import android.content.Context
import android.util.DisplayMetrics

class Util {

    companion object {
        fun convertDpToPixel(dp: Int, context: Context): Int {
            return (dp * (context.resources
                .displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
        }
    }

}