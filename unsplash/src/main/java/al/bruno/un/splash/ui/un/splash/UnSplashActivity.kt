package al.bruno.un.splash.ui.un.splash

import al.bruno.di.base.BaseActivity
import al.bruno.un.splash.R
import al.bruno.un.splash.model.api.Search
import al.bruno.un.splash.ui.un.splash.collections.CollectionsFragment
import al.bruno.un.splash.ui.un.splash.home.HomeFragment
import al.bruno.un.splash.utils.MyRxBus
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.AndroidInjection
import javax.inject.Inject

class UnSplashActivity : BaseActivity() {
    @Inject lateinit var myRxBusSearch: MyRxBus
    val search = Search(null, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_un_splash)
        val viewPager: ViewPager2 = findViewById(R.id.un_splash_view_pager)
        val tabLayout: TabLayout = findViewById(R.id.un_splash_tab_layout)
        setSupportActionBar(findViewById(R.id.un_splash_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 2
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> HomeFragment()
                    1 -> CollectionsFragment()
                    else -> HomeFragment()
                }
            }
        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getStringArray(R.array.un_splash_tabs)[position]
        }.attach()

        viewPager.offscreenPageLimit = 2
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            // TODO
        })
    }
    override fun onResume() {
        super.onResume()
        /*if (mEditText.getText().toString() != "") {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}