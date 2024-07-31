package uz.alien.ui

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Build
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import uz.alien.pager.Pager
import uz.alien.ui.databinding.ParentMainBinding
import uz.alien.ui.pages.PageFeature
import uz.alien.ui.pages.PageHome
import uz.alien.ui.pages.PageWelcome
import uz.alien.ui.sides.SideProfile
import uz.alien.ui.sides.SideSettings

@SuppressLint("StaticFieldLeak")
class MainPager : Pager() {

    lateinit var binding: ParentMainBinding

    override fun init(activity: AppCompatActivity) {
        super.init(activity)

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


        PageWelcome(this).init()
        PageFeature(this).init()
        PageHome(this).init()

        SideProfile(this).init()
        SideSettings(this).init()

        setBackground(binding.background) {

            binding.parentPage.alpha = 1.0f
            binding.parentSide.alpha = 1.0f
            binding.parentMove.alpha = 1.0f
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var self: MainPager
    }
}