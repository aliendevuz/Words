package uz.alien.ui

import android.annotation.SuppressLint
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import uz.alien.pager.Pager
import uz.alien.ui.databinding.ParentMainBinding
import uz.alien.ui.pages.PageHome
import uz.alien.ui.pages.PageWelcome

@SuppressLint("StaticFieldLeak")
class MainPager : Pager() {

    lateinit var binding: ParentMainBinding

    override fun init(activity: AppCompatActivity) {
        super.init(activity)

        self = this

        binding = ParentMainBinding.inflate(activity.layoutInflater)

        activity.setContentView(binding.root)

        binding.root.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {

            override fun onGlobalLayout() {

                binding.root.viewTreeObserver.removeOnGlobalLayoutListener(this)

                density = activity.resources.displayMetrics.density
                screenWidth = activity.resources.displayMetrics.widthPixels
                screenHeight = activity.resources.displayMetrics.heightPixels
            }
        })

        lockPage = binding.lockPage

        lockSide = binding.lockSide

        lockMove = binding.lockMove


        PageWelcome(this).init()
        PageHome(this).init()

        hideBackSplashScreen(binding.backSplashscreen.root)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var self: MainPager
    }
}