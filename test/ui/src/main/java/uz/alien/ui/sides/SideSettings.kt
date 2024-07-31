package uz.alien.ui.sides

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import uz.alien.pager.Side
import uz.alien.ui.MainPager

class SideSettings(override val pager: MainPager) : Side(pager) {

    val binding = MainPager.self.binding.sideForeSettings

    override val background = MainPager.self.binding.sideBackSettings.root
    override val foreground = binding.root

    override val back = foreground
    override val side = binding.sideSettings

    override val target = MainPager.self.binding.nothing

    override val direction = Direction.RIGHT
    override val occupied = 1.0f

    fun init() {

        self = this

        handler = Handler(Looper.getMainLooper())

        background.alpha = 0.0f

        sidePreparer {

            sideSwipePreparer()

            setBackHider()
        }

        binding.bOpenSecond.setOnClickListener {
            //
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var self: SideSettings
    }
}