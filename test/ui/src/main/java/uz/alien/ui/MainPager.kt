package uz.alien.ui

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import kotlinx.coroutines.delay
import uz.alien.constant.Strings
import uz.alien.manager.SharedPrefs
import uz.alien.pager.Pager
import uz.alien.ui.databinding.ParentMainBinding
import uz.alien.ui.pages.PageFeature
import uz.alien.ui.pages.PageHome
import uz.alien.ui.pages.PageWelcome
import uz.alien.ui.sides.SideProfile
import uz.alien.ui.sides.SideSettings

@SuppressLint("StaticFieldLeak")
open class MainPager(activity: AppCompatActivity) : Pager(activity) {

    lateinit var binding: ParentMainBinding

    var welcome: PageWelcome? = null
    var feature: PageFeature? = null
    var home: PageHome? = null

    override fun init() {
        super.init()
        self = this
        binding = ParentMainBinding.inflate(activity.layoutInflater)
        root = binding.root
        activity.setContentView(root)
        root.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                root.viewTreeObserver.removeOnGlobalLayoutListener(this)
                density = activity.resources.displayMetrics.density
                screenWidth = activity.resources.displayMetrics.widthPixels
                screenHeight = activity.resources.displayMetrics.heightPixels
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    activity.window.systemGestureExclusionRects = listOf(Rect(0, screenHeight - (200).toInt(), (24 * density).toInt(), screenHeight))
                }
            }
        })
        lockPage = binding.lockPage
        lockSide = binding.lockSide
        lockMove = binding.lockMove
//        welcome.init()
//        feature.init()
//        welcome = PageWelcome(this).apply { init() }
//        feature = PageFeature(this).apply { init() }
//        home = PageHome(this).apply { init() }

        binding.ivLogo.visibility = View.VISIBLE
        binding.ivLogo.startAnimation(AnimationSet(true).apply {
            addAnimation(AlphaAnimation(0.0f, 1.0f))
            duration = this@MainPager.duration * 5
        })
        binding.ivLogo.alpha = 1.0f

        handler.postDelayed(duration * 20) {

            binding.ivLogo.startAnimation(AnimationSet(true).apply {
                addAnimation(AlphaAnimation(1.0f, 0.0f))
                duration = this@MainPager.duration * 3
            })

            handler.postDelayed(duration * 3) {
                binding.ivLogo.alpha = 0.0f
                binding.ivLogo.visibility = View.GONE

                handler.postDelayed(duration * 2) {

                    setBackground(binding.background) {

                        val pageHome = home!!.foreground
                        pageHome.alpha = 1.0f
                        pageHome.visibility = View.VISIBLE

//                        pageHome.measure(
//                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
//                        )
//                        pageHome.layout(0, 0, pageHome.measuredWidth, pageHome.measuredHeight)

//                        pageHome.setDrawingCacheEnabled(true)
//                        pageHome.buildDrawingCache()

//                        Toast.makeText(activity, "Accepted!", Toast.LENGTH_SHORT).show()

                        handler.postDelayed(200) {

//                            pageHome.visibility = View.GONE
                            pageHome.alpha = 1.0f

                            binding.parentPage.alpha = 1.0f
                            binding.parentSide.alpha = 1.0f
                            binding.parentMove.alpha = 1.0f
                        }

                    }
                }
            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var self: MainPager
    }
}