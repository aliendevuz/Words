package uz.alien.wordstesting

import android.os.Bundle
import android.os.Handler
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import uz.alien.constant.Strings
import uz.alien.linker.main.Main
import uz.alien.manager.Notification
import uz.alien.manager.SharedPrefs

class Root : AppCompatActivity() {

    private var main: Main? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init managers
        SharedPrefs.init(this, Strings.SETTINGS)
        Notification.init(this)

        // Custom splash screen initializer
        Thread.sleep(274L)
        installSplashScreen()

        // Splash screen hiding
        enableEdgeToEdge()
        setContentView(LinearLayout(this)) // for not show App bar layout, cause main root not been initialize!
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        insetsController.hide(WindowInsetsCompat.Type.systemBars()) // hide system bar before hiding splash screen

        // Give back press controller to mainPager
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                main?.back()
            }
        })

        // Init mainPager
        Handler(mainLooper).post {
            main = Main(this)
            main?.init()
        }
    }

    // give onWindowFocusChanged action to mainPager
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        main?.changeFocus(hasFocus)
    }
}