package uz.alien.wordstesting

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import uz.alien.constants.Strings
import uz.alien.manager.Notification
import uz.alien.manager.SharedPrefs
import uz.alien.ui.MainPager
import uz.alien.ui.pages.PageHome
import uz.alien.ui.pages.PageWelcome

class MainActivity : AppCompatActivity() {

    private var mainPager: MainPager? = null

    private var handler: Handler? = null

    private var insetsController: WindowInsetsControllerCompat? = null
    private val root: View? = null

    private fun showSystemBars() {
        insetsController?.show(WindowInsetsCompat.Type.systemBars())
    }

    private fun hideSystemBars() {
        insetsController?.hide(WindowInsetsCompat.Type.systemBars())
    }

    private fun fitWindow() {
        root?.let {
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                v.setPadding(0, 0, 0, 0)
                insets
            }
            root.requestApplyInsets()
        }
    }

    private fun defaultWindow() {
        root?.let {
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            root.requestApplyInsets()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handler = Handler(mainLooper)

        Notification.init(this)

        Thread.sleep(1096L)
        installSplashScreen()  // Custom splash screen initializer

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                mainPager?.back(this@MainActivity)
            }
        })

        setContentView(LinearLayout(this)) // for not show App bar layout, cause main root not been initialize!

        insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController?.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        hideSystemBars()  // hide system bar before hiding splash screen

        enableEdgeToEdge()

        handler?.post {

            mainPager = MainPager().apply {  // init MainPager

                init(this@MainActivity)

                if (SharedPrefs.getBoolean(Strings.SETTINGS_WELCOME_ACCEPTED)) {

                    firstPage(PageHome.self, 8)
                } else {

                    firstPage(PageWelcome.self, 72, 1096L)
                }

                insetsController = WindowCompat.getInsetsController(window, window.decorView)
                insetsController?.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {

        super.onWindowFocusChanged(hasFocus)

        mainPager?.changeFocus(hasFocus)
    }
}