package uz.alien.ui.pages

import android.annotation.SuppressLint
import android.view.View
import uz.alien.constants.Strings
import uz.alien.manager.SharedPrefs
import uz.alien.pager.Page
import uz.alien.ui.MainPager

open class PageFeature(private val pager: MainPager) : Page() {

    private val binding = pager.binding.pageForeFeature

    override val background = pager.binding.pageBackFeature.root
    override val foreground = binding.root

    override var isBackable = false

    override fun init() {
        super.init()

        self = this

        background.alpha = 0.0f
        background.visibility = View.GONE

        foreground.alpha = 0.0f
        foreground.visibility = View.GONE

        binding.bGetStart.setOnClickListener {

            SharedPrefs.saveValue(Strings.SETTINGS_WELCOME_ACCEPTED, true)

            pager.openPage(PageHome.self)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var self: PageFeature
    }
}