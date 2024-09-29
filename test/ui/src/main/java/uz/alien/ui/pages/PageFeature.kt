package uz.alien.ui.pages

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.core.os.postDelayed
import uz.alien.constant.Strings
import uz.alien.manager.SharedPrefs
import uz.alien.pager.Page
import uz.alien.ui.MainPager

open class PageFeature(val pager: MainPager) : Page() {

    private val binding = pager.binding.pageForeFeature

    override val background = pager.binding.pageBackFeature.root
    override val foreground = binding.root

    override var isBackable = false

    val bGetStart = binding.bGetStart

    override fun init() {
        super.init()
        background.alpha = 0.0f
        background.visibility = View.GONE
        foreground.alpha = 0.0f
        foreground.visibility = View.GONE

        binding.bGetStart.setOnClickListener {
            SharedPrefs.saveValue(Strings.SETTINGS_WELCOME_ACCEPTED, true)



//            handler.postDelayed(3000L) {

                MainPager.self.openPage(MainPager.self.home!!)
//            }
        }
    }
}