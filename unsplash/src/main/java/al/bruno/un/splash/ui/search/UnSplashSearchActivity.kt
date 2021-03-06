package al.bruno.un.splash.ui.search

import al.bruno.di.base.BaseActivity
import al.bruno.un.splash.R
import al.bruno.un.splash.databinding.ActivityUnSplashSearchBinding
import al.bruno.un.splash.model.api.Search
import al.bruno.un.splash.ui.search.collection.SearchCollectionFragment
import al.bruno.un.splash.ui.search.photo.SearchPhotoFragment
import al.bruno.un.splash.ui.search.user.SearchUserFragment
import al.bruno.un.splash.utils.MyRxBus
import android.os.Bundle
import android.view.KeyEvent
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class UnSplashSearchActivity : BaseActivity() {
    @Inject
    lateinit var myRxBusSearch: MyRxBus

    val search = Search(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activitySearchBinding = ActivityUnSplashSearchBinding.inflate(layoutInflater)
        setContentView(activitySearchBinding.root)
        activitySearchBinding.unSplashToolbar.setNavigationOnClickListener {
            finish()
        }
        activitySearchBinding.unSplashViewPager.isUserInputEnabled = false
        activitySearchBinding.unSplashViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> SearchPhotoFragment()
                    1 -> SearchCollectionFragment()
                    2 -> SearchUserFragment()
                    else -> SearchPhotoFragment()
                }
            }
        }
        TabLayoutMediator(
            activitySearchBinding.unSplashTabLayout,
            activitySearchBinding.unSplashViewPager
        ) { tab, position ->
            tab.text = resources.getStringArray(R.array.un_splash_tabs)[position]
        }.attach()

        activitySearchBinding.unSplashViewPager.offscreenPageLimit = 2
        activitySearchBinding.unSplashViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            // TODO
        })

        activitySearchBinding.unSplashTextInput.setOnEditorActionListener { textView: TextView, i: Int, keyEvent: KeyEvent? ->
            if (i == KeyEvent.ACTION_DOWN /*&& keyEvent?.keyCode == KeyEvent.KEYCODE_SEARCH*/) {
                search.query = textView.text
                myRxBusSearch.setRxBus(search)
                window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        activitySearchBinding.unSplashTextInput.isFocusable = true
        activitySearchBinding.unSplashTextInput.requestFocus()

        if (activitySearchBinding.unSplashTextInput.requestFocus() && activitySearchBinding.unSplashTextInput.text.toString() == "") {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        }
    }
}