package uz.alien.linker.main

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import uz.alien.constant.Strings
import uz.alien.linker.main.pages.Home
import uz.alien.manager.SharedPrefs
import uz.alien.ui.MainPager
import uz.alien.ui.pages.PageFeature
import uz.alien.ui.pages.PageWelcome
import uz.alien.ui.sides.SideProfile
import uz.alien.ui.sides.SideSettings

class Main(activity: AppCompatActivity) : MainPager(activity) {

    override fun init() {
        super.init()
        welcome = PageWelcome(this).apply { init() }
        feature = PageFeature(this).apply { init() }
        home = Home(this).apply { init() }
        SideProfile(this).init()
        SideSettings(this).init()
        if (SharedPrefs.getBoolean(Strings.SETTINGS_WELCOME_ACCEPTED)) {
            firstPage(home!!, 8, 1096L)
        } else {
            firstPage(PageWelcome.self, 80, 1096L)
        }
        insetsController = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
        insetsController?.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}