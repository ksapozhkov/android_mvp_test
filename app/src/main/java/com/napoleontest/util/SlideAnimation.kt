package com.napoleontest.util

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.doOnStart

class SlideAnimation(
    private val view: View,
    private val fromHeight: Int,
    private val toHeight: Int,
    private val duration: Long
) {

    fun start() {
        val animator = getValueAnimator(
            duration, AccelerateDecelerateInterpolator()
        ) { progress ->
            view.layoutParams.height =
                (fromHeight + (toHeight - fromHeight) * progress).toInt()
            view.requestLayout()
        }
        animator.doOnStart { view.visibility = View.VISIBLE }
        animator.start()
    }

    private fun getValueAnimator(
        duration: Long, interpolator: TimeInterpolator,
        updateListener: (progress: Float) -> Unit
    ): ValueAnimator {
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener { updateListener(it.animatedValue as Float) }
        animator.duration = duration
        animator.interpolator = interpolator
        return animator
    }

}