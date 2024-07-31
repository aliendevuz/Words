package uz.alien.wordstesting

import android.app.Application
import uz.alien.constants.Strings
import uz.alien.manager.SharedPrefs

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        SharedPrefs.init(this, Strings.SETTINGS)
    }
}