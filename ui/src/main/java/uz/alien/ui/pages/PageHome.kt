package uz.alien.ui.pages

import android.annotation.SuppressLint
import android.view.View
import uz.alien.pager.Page
import uz.alien.ui.MainPager

class PageHome(pager: MainPager) : Page() {

    val binding = pager.binding.pageForeHome

    override val background = pager.binding.pageBackHome.root
    override val foreground = binding.root

    override fun init() {
        super.init()

        self = this

        background.alpha = 0.0f
        background.visibility = View.GONE

        foreground.alpha = 0.0f
        foreground.visibility = View.GONE
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var self: PageHome
    }
}