package uz.alien.linker.main.pages

import uz.alien.constant.Strings
import uz.alien.manager.SharedPrefs
import uz.alien.ui.MainPager
import uz.alien.ui.pages.PageFeature
import uz.alien.ui.pages.PageHome

class Feature(pager: MainPager) : PageFeature(pager) {
    init {
        bGetStart.setOnClickListener {
            SharedPrefs.saveValue(Strings.SETTINGS_WELCOME_ACCEPTED, true)
            pager.home?.let { it1 -> pager.openPage(it1) }
        }
    }
}