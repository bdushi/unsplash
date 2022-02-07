package al.bruno.un.splash.ui.search

import PHOTO
import al.bruno.adapter.OnClickListener
import al.bruno.di.base.BaseActivity
import al.bruno.un.splash.R
import al.bruno.un.splash.databinding.ActivitySearchBinding
import al.bruno.un.splash.model.api.Photo
import al.bruno.un.splash.model.api.Search
import al.bruno.un.splash.ui.search.collection.SearchCollectionFragment
import al.bruno.un.splash.ui.search.photo.SearchPhotoFragment
import al.bruno.un.splash.ui.search.user.SearchUserFragment
import al.bruno.un.splash.utils.MyRxBus
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class SearchActivity : BaseActivity() {
    @Inject
    lateinit var myRxBusSearch: MyRxBus

    lateinit var activitySearchBinding: ActivitySearchBinding

    val search = Search(null, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySearchBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(activitySearchBinding.root)
        setSupportActionBar(activitySearchBinding.unSplashToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activitySearchBinding.unSplashViewPager.isUserInputEnabled = false
        activitySearchBinding.unSplashViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> SearchPhotoFragment().setOnClickListener(object : OnClickListener<Photo> {
                        override fun onClick(t: Photo) {
                            setResult(Activity.RESULT_OK, Intent().putExtra(PHOTO, t.urls.regular))
                            finish()
                        }
                    })
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

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}