package uz.alien.ui.sides

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.MotionEvent
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import uz.alien.pager.Side
import uz.alien.ui.MainPager
import uz.alien.ui.pages.PageHome

class SideProfile(override val pager: MainPager) : Side(pager) {

    val binding = MainPager.self.binding.sideForeProfile

    override val background = MainPager.self.binding.sideBackProfile.root
    override val foreground = MainPager.self.binding.parentPage

    override val back = binding.root
    override val side = binding.sideProfile

    override val target = pager.home!!.foreground

    override val direction = Direction.LEFT

    fun init() {

        self = this

        handler = Handler(Looper.getMainLooper())

        latencyHandler = Handler(Looper.getMainLooper())

        background.alpha = 0.0f

        sidePreparer {

            sideSwipePreparer()

//            foreground.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, side)

//            side.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.START)

//            setBackSwiper(Rect(
//                0,
//                (108.0f * pager.density).toInt(),
//                (32.0f * pager.density).toInt(),
//                height.toInt()
//            ))
            setBackSwiper(Rect(
                0,
                0,
                (64.0f * pager.density).toInt(),
                height.toInt()
            ))
        }

        binding.bOpenSettings.setOnClickListener {

//            MainPager.self.hideSide(MainPager.self.duration * 2)

//            handler.postDelayed(MainPager.self.duration) {

                pager.showSide(SideSettings.self)
//            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var self: SideProfile
    }
}