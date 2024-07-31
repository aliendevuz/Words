package uz.alien.ui.pages

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import uz.alien.pager.Page
import uz.alien.ui.MainPager
import uz.alien.ui.sides.SideProfile

open class PageHome(val pager: MainPager) : Page() {

    private val binding = pager.binding.pageForeHome

    override val background = pager.binding.pageBackHome.root
    override val foreground = binding.root

    var isSearching = false

    override fun onPause(): Boolean {
        return if (isSearching) {
            hideSearch()
            false
        } else {
            super.onPause()
        }
    }

    fun showSearch() {

        isSearching = true

        binding.lockSearch.visibility = View.VISIBLE

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            (48 * MainPager.self.density).toInt()
        ).apply {
            gravity = Gravity.CENTER_VERTICAL or Gravity.END
        } as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(
            (24 * MainPager.self.density).toInt(),
            (12 * MainPager.self.density).toInt(),
            (24 * MainPager.self.density).toInt(),
            0
        )

        binding.cvSearch.layoutParams = layoutParams

        val layoutParams2 = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            weight = 1.0f
//            gravity = Gravity.CENTER_VERTICAL or Gravity.END
        } as ViewGroup.MarginLayoutParams
        layoutParams2.setMargins(
            (20 * MainPager.self.density).toInt(),
            (0 * MainPager.self.density).toInt(),
            (12 * MainPager.self.density).toInt(),
            (0 * MainPager.self.density).toInt()
        )

        binding.etSearch.layoutParams = layoutParams2

        binding.ibSearch.setPadding(
            (0 * MainPager.self.density).toInt(),
            (8 * MainPager.self.density).toInt(),
            (9 * MainPager.self.density).toInt(),
            (8 * MainPager.self.density).toInt()
        )

        binding.backSearch.setOnClickListener {
            hideSearch()
        }

        binding.backSearch.visibility = View.VISIBLE
        binding.backSearch.startAnimation(AnimationSet(true).apply {
            addAnimation(
                AlphaAnimation(
                    0.0f,
                    1.0f
                ).apply {
                    duration = pager.duration * 2
                }
            )
        })

        handler.postDelayed(pager.duration * 2) {
            binding.lockSearch.visibility = View.GONE
        }

        binding.etSearch.requestFocus()
        (pager.activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(binding.etSearch, InputMethodManager.SHOW_IMPLICIT)
    }

    fun hideSearch() {

        isSearching = false
        binding.lockSearch.visibility = View.VISIBLE

        val layoutParams = LinearLayout.LayoutParams(
            (48 * MainPager.self.density).toInt(),
            (48 * MainPager.self.density).toInt()
        ).apply {
            gravity = Gravity.CENTER_VERTICAL or Gravity.END
        } as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(
            (24 * MainPager.self.density).toInt(),
            (12 * MainPager.self.density).toInt(),
            (24 * MainPager.self.density).toInt(),
            0
        )

        binding.cvSearch.layoutParams = layoutParams

        val layoutParams2 = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            weight = 0.0f
        } as ViewGroup.MarginLayoutParams
        layoutParams2.setMargins(
            (0 * MainPager.self.density).toInt(),
            (0 * MainPager.self.density).toInt(),
            (0 * MainPager.self.density).toInt(),
            (0 * MainPager.self.density).toInt()
        )

        binding.etSearch.layoutParams = layoutParams2

        binding.ibSearch.setPadding(
            (8 * MainPager.self.density).toInt(),
            (8 * MainPager.self.density).toInt(),
            (8 * MainPager.self.density).toInt(),
            (8 * MainPager.self.density).toInt()
        )

        binding.backSearch.startAnimation(AnimationSet(true).apply {
            addAnimation(
                AlphaAnimation(
                    1.0f,
                    0.0f
                ).apply {
                    duration = pager.duration * 2
                }
            )
        })

        handler.postDelayed(pager.duration * 2) {
            binding.backSearch.visibility = View.GONE
            binding.lockSearch.visibility = View.GONE
        }

        (pager.activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(binding.etSearch.windowToken, 0)

        binding.etSearch.text.clear()
    }

    override fun init() {
        super.init()

        self = this

        background.alpha = 0.0f
        background.visibility = View.GONE

        foreground.alpha = 0.0f
        foreground.visibility = View.GONE

        binding.ibPanelTv.setOnClickListener {
            if (!isSearching) {
                pager.showSide(SideProfile.self)
            }
        }

        binding.ibPanelIv.setOnClickListener {
            if (!isSearching) {
                pager.showSide(SideProfile.self)
            }
        }

        binding.bGeneral.setOnClickListener {
            pager.openPage(self)
        }

        binding.bb1.setOnClickListener {
            //
        }

        binding.bb2.setOnClickListener {
            //
        }

        binding.bb3.setOnClickListener {
            //
        }

        binding.bb4.setOnClickListener {
            //
        }

        binding.eb1.setOnClickListener {
            //
        }

        binding.eb2.setOnClickListener {
            //
        }

        binding.eb3.setOnClickListener {
            //
        }

        binding.eb4.setOnClickListener {
            //
        }

        binding.eb5.setOnClickListener {
            //
        }

        binding.eb6.setOnClickListener {
            //
        }

        binding.ibSearch.setOnClickListener {

            if (isSearching) {
                if (binding.etSearch.text.isEmpty()) {
                    hideSearch()
                } else {
                    // Searching...
                }
            } else {
                showSearch()
            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var self: PageHome
    }
}