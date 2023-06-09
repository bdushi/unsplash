package al.bruno.un.splash.ui.search

import al.bruno.di.base.BaseActivity
import al.bruno.un.splash.R
import al.bruno.un.splash.model.Search
import al.bruno.un.splash.databinding.ActivityUnSplashSearchBinding
import al.bruno.un.splash.ui.search.collection.SearchCollectionFragment
import al.bruno.un.splash.ui.search.photo.SearchPhotoFragment
import al.bruno.un.splash.ui.search.user.SearchUserFragment
import android.os.Bundle
import android.view.KeyEvent
import android.view.WindowManager
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.AndroidInjector

class UnSplashSearchActivity : BaseActivity() {
    private val unsplash = arrayListOf(SearchPhotoFragment(), SearchCollectionFragment(), SearchUserFragment())
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelProvider)[UnSplashSearchViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activitySearchBinding = ActivityUnSplashSearchBinding.inflate(layoutInflater)
        setContentView(activitySearchBinding.root)
        activitySearchBinding.unSplashToolbar.setNavigationOnClickListener {
            finish()
        }
        activitySearchBinding.unSplashViewPager.isUserInputEnabled = false
        activitySearchBinding.unSplashViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = unsplash.size

            override fun createFragment(position: Int) = unsplash[position]
        }
        TabLayoutMediator(
            activitySearchBinding.unSplashTabLayout,
            activitySearchBinding.unSplashViewPager
        ) { tab, position ->
            tab.text = resources.getStringArray(R.array.un_splash_tabs)[position]
        }.attach()

        activitySearchBinding.unSplashTextInput.setOnEditorActionListener { textView: TextView, i: Int, keyEvent: KeyEvent? ->
            if (i == KeyEvent.ACTION_DOWN /*&& keyEvent?.keyCode == KeyEvent.KEYCODE_SEARCH*/) {
                viewModel.search.value = Search(textView.text)
                window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        activitySearchBinding.unSplashTextInput.isFocusable = true
        activitySearchBinding.unSplashTextInput.requestFocus()

        if (activitySearchBinding.unSplashTextInput.requestFocus() && activitySearchBinding.unSplashTextInput.text?.isEmpty() == true) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentInjector
    }
}