package uz.alien.linker.settings

import android.graphics.Rect
import android.os.Build
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import uz.alien.constant.Strings
import uz.alien.manager.SharedPrefs
import uz.alien.ui.MainPager
import uz.alien.ui.databinding.ParentMainBinding
import uz.alien.ui.pages.PageFeature
import uz.alien.ui.pages.PageHome
import uz.alien.ui.pages.PageWelcome
import uz.alien.ui.sides.SideProfile
import uz.alien.ui.sides.SideSettings

class Main(activity: AppCompatActivity) : MainPager(activity) {

//    override fun init() {
//        super.init()
//        self = this
//        _binding = ParentMainBinding.inflate(activity.layoutInflater)
//        binding = _binding as ParentMainBinding
//        root = binding!!.root
//        activity.setContentView(root)
//        root.viewTreeObserver.addOnGlobalLayoutListener(object :
//            ViewTreeObserver.OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                root.viewTreeObserver.removeOnGlobalLayoutListener(this)
//                density = activity.resources.displayMetrics.density
//                screenWidth = activity.resources.displayMetrics.widthPixels
//                screenHeight = activity.resources.displayMetrics.heightPixels
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                    activity.window.systemGestureExclusionRects = listOf(Rect(0, screenHeight - (200).toInt(), (24 * density).toInt(), screenHeight))
//                }
//            }
//        })
//        lockPage = binding.lockPage
//        lockSide = binding.lockSide
//        lockMove = binding.lockMove
//        welcome.init()
//        feature.init()
//        home.init()
//
//        home.setBinding(binding.pageForeHome)
//        SideProfile(this).init()
//        SideSettings(this).init()
//        setBackground(binding.background) {
//            binding.parentPage.alpha = 1.0f
//            binding.parentSide.alpha = 1.0f
//            binding.parentMove.alpha = 1.0f
//        }
//        if (SharedPrefs.getBoolean(Strings.SETTINGS_WELCOME_ACCEPTED)) {
//            firstPage(PageHome.self, 8)
//        } else {
//            firstPage(PageWelcome.self, 72, 1096L)
//        }
//        this.insetsController = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
//        this.insetsController?.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//    }
}