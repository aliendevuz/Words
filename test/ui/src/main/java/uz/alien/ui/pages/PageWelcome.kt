package uz.alien.ui.pages

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import androidx.core.os.postDelayed
import uz.alien.pager.Page
import uz.alien.ui.MainPager

open class PageWelcome(private val pager: MainPager) : Page() {

    private val binding = pager.binding.pageForeWelcome

    override val background = pager.binding.pageBackWelcome.root
    override val foreground = binding.root

    override var isBackable = false

    private var margin = 0.0f

    override var isCustomBackAnimate = true

    override fun init() {
        super.init()

        self = this

        background.alpha = 0.0f
        background.visibility = View.GONE

        foreground.alpha = 0.0f
        foreground.visibility = View.GONE
    }

    override fun onStart(): Boolean {

        binding.lWelcomeLogoBackground.y += margin

        // logo
        val layoutParams = binding.lWelcomeLogoBackground.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(0, 0, 0, 0)
        binding.lWelcomeLogoBackground.layoutParams = layoutParams
        // logos
        binding.ivWelcomeLogo1.alpha = 0.0f
        binding.ivWelcomeLogo2.alpha = 0.0f
        binding.ivWelcomeLogo3.alpha = 0.0f
        // text
        binding.tvWelcomeBrand.alpha = 0.0f
        binding.tvWelcomeProduct.alpha = 0.0f

        handler.postDelayed(pager.duration * 9) {

            startWelcome()
        }
        return false
    }

    private fun startWelcome() {

        pager.density
        val height = pager.screenHeight
        Log.d("@@@@", "$height")
        binding.lWelcomeLogoBackground.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
//        val logoHeight = binding.lWelcomeLogoBackground.measuredHeight
        val pos1 = height / 2
        Log.d("@@@@", "$pos1 y position")
        Log.d("@@@@", "Logo top = ${binding.lWelcomeLogoBackground.y}")
        margin = height.toFloat() / 6

        background.startAnimation(AnimationSet(true).apply {
            addAnimation(AlphaAnimation(0f, 1f))
            this.duration = 600L
            this.interpolator = LinearInterpolator()
        })
        background.alpha = 1.0f
        foreground.alpha = 1.0f

        handler.postDelayed(1400L) {
            binding.ivWelcomeLogo1.alpha = 1.0f
            binding.ivWelcomeLogo1.startAnimation(AnimationSet(true).apply {
                addAnimation(AlphaAnimation(0f, 1f))
                this.duration = 300L
                this.interpolator = LinearInterpolator()
            })

            Log.d("@@@@", "logo1")
            handler.postDelayed(1000L) {
                binding.ivWelcomeLogo2.alpha = 1.0f
                binding.ivWelcomeLogo2.startAnimation(AnimationSet(true).apply {
                    addAnimation(AlphaAnimation(0f, 1f))
                    this.duration = 300L
                    this.interpolator = LinearInterpolator()
                })

                Log.d("@@@@", "logo 2")
                handler.postDelayed(1000L) {
                    binding.ivWelcomeLogo3.alpha = 1.0f
                    binding.ivWelcomeLogo3.startAnimation(AnimationSet(true).apply {
                        addAnimation(AlphaAnimation(0f, 1f))
                        this.duration = 300L
                        this.interpolator = LinearInterpolator()
                    })

                    handler.postDelayed(1000L) {
                        binding.lWelcomeLogoBackground.y -= margin
                        binding.lWelcomeLogoBackground.startAnimation(AnimationSet(true).apply {
                            addAnimation(
                                TranslateAnimation(
                                    0.0f,
                                    0.0f,
                                    margin,
                                    0.0f
                                )
                            )
                            this.duration = 600L
                            this.interpolator = AccelerateDecelerateInterpolator()
                        })

                        Log.d("@@@@", "logo 3")
                        handler.postDelayed(1600L) {
                            binding.tvWelcomeBrand.alpha = 1.0f
                            binding.tvWelcomeBrand.startAnimation(AnimationSet(true).apply {
                                addAnimation(AlphaAnimation(0f, 1f))
                                this.duration = 300L
                                this.interpolator = LinearInterpolator()
                            })

                            Log.d("@@@@", "brand")
                            handler.postDelayed(1000L) {
                                binding.tvWelcomeProduct.alpha = 1.0f
                                binding.tvWelcomeProduct.startAnimation(AnimationSet(true).apply {
                                    addAnimation(AlphaAnimation(0f, 1f))
                                    this.duration = 300L
                                    this.interpolator = LinearInterpolator()
                                })

                                Log.d("@@@@", "production")
                                handler.postDelayed(2000L) {

                                    background.startAnimation(AnimationSet(true).apply {
                                        addAnimation(AlphaAnimation(1f, 0f))
                                        this.duration = 600L
                                        this.interpolator = LinearInterpolator()
                                    })

                                    handler.postDelayed(600L) {
                                        background.visibility = View.GONE
                                    }

                                    Log.d("@@@@", "welcome background hide")
                                    pager.feature?.let { pager.openPage(it) }
                                    Log.d("@@@@", "start welcome successfully finished!")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var self: PageWelcome
    }
}