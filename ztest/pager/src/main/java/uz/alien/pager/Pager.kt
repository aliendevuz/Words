package uz.alien.pager

import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

open class Pager(private val activity: AppCompatActivity) {

    // Main Handler

    private val handler = android.os.Handler(Looper.getMainLooper())


    // Insets Controller
    private var insetsController: WindowInsetsControllerCompat? = null

    fun showNavigation() {
        insetsController?.show(WindowInsetsCompat.Type.navigationBars())
    }

    fun hideNavigation() {
        insetsController?.hide(WindowInsetsCompat.Type.navigationBars())
    }

    fun showSystemBars() {
        insetsController?.show(WindowInsetsCompat.Type.systemBars())
    }

    fun hideSystemBars() {
        insetsController?.hide(WindowInsetsCompat.Type.systemBars())
    }

    private fun moveTaskToBack() {
        activity.moveTaskToBack(true)
    }


    // Side Exception

    var showingSide: Side? = null


    // Dimens

    open var density = 0.0f

    open var screenWidth = 0
    open var screenHeight = 0


    // Animation values

    open val duration = STANDARD_DURATION * TIMES_DURATION







    companion object {
        const val STANDARD_DURATION = 137
        const val TIMES_DURATION = 1
    }
}