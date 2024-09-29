package uz.alien.pager

import android.content.Context
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import android.view.animation.ScaleAnimation
import android.widget.FrameLayout

open class Page(context: Context) {

    open var background: ViewGroup = FrameLayout(context)
    open var foreground: ViewGroup = FrameLayout(context)

    open var isBackable = true
    open var isCustomBackAnimate = false

    open fun onStart(): Boolean {
        return true
    }

    open fun onPause(): Boolean {
        return true
    }

    companion object {

        // Background animations

        fun pageBackInFastOut(duration: Long): AnimationSet {
            return AnimationSet(true).apply {
                addAnimation(
                    AlphaAnimation(0f, 1f).apply {
                        this.duration = duration
                        this.interpolator = DecelerateInterpolator()
                    }
                )
            }
        }

        fun pageBackOutFastIn(duration: Long): AnimationSet {
            return AnimationSet(true).apply {
                addAnimation(
                    AlphaAnimation(1f, 0f).apply {
                        this.duration = duration
                        this.interpolator = AccelerateInterpolator()
                    }
                )
            }
        }


        // Foreground Animations

        fun pageForeInZoomIn(duration: Long): AnimationSet {
            return AnimationSet(true).apply {
                addAnimation(
                    ScaleAnimation(
                        0.9f, 1.0f,
                        0.9f, 1.0f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f
                    ).apply {
                        this.duration = duration
                        this.interpolator = AccelerateInterpolator()
                    }
                )
                addAnimation(
                    AlphaAnimation(0f, 1f).apply {
                        this.duration = duration
                        this.interpolator = DecelerateInterpolator()
                    }
                )
            }
        }

        fun pageForeInZoomOut(duration: Long): AnimationSet {
            return AnimationSet(true).apply {
                addAnimation(
                    ScaleAnimation(
                        1.111111f, 1.0f,
                        1.111111f, 1.0f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f
                    ).apply {
                        this.duration = duration
                        this.interpolator = DecelerateInterpolator()
                    }
                )
                addAnimation(
                    AlphaAnimation(0f, 1f).apply {
                        this.duration = duration
                        this.interpolator = DecelerateInterpolator()
                    }
                )
            }
        }

        fun pageForeOutZoomIn(duration: Long): AnimationSet {
            return AnimationSet(true).apply {
                addAnimation(
                    ScaleAnimation(
                        1.0f, 0.9f,
                        1.0f, 0.9f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f
                    ).apply {
                        this.duration = duration
                        this.interpolator = DecelerateInterpolator()
                    }
                )
                addAnimation(
                    AlphaAnimation(1f, 0f).apply {
                        this.duration = duration
                        this.interpolator = AccelerateInterpolator()
                    }
                )
            }
        }

        fun pageForeOutZoomOut(duration: Long): AnimationSet {
            return AnimationSet(true).apply {
                addAnimation(
                    ScaleAnimation(
                        1f, 1.111111f,
                        1f, 1.111111f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f
                    ).apply {
                        this.duration = duration
                        this.interpolator = AccelerateInterpolator()
                    }
                )
                addAnimation(
                    AlphaAnimation(1f, 0f).apply {
                        this.duration = duration
                        this.interpolator = AccelerateInterpolator()
                    }
                )
            }
        }
    }
}