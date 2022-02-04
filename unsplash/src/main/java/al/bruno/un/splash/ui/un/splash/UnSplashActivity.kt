package al.bruno.un.splash.ui.un.splash

import al.bruno.di.base.BaseActivity
import al.bruno.un.splash.R
import al.bruno.un.splash.databinding.ActivityUnSplashBinding
import al.bruno.un.splash.model.api.Search
import al.bruno.un.splash.ui.search.SearchActivity
import al.bruno.un.splash.ui.un.splash.collections.CollectionsFragment
import al.bruno.un.splash.ui.un.splash.photo.PhotoFragment
import al.bruno.un.splash.utils.MyRxBus
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class UnSplashActivity : BaseActivity() {

    lateinit var binding: ActivityUnSplashBinding
    @Inject lateinit var myRxBusSearch: MyRxBus
    val search = Search(null, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnSplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.unSplashViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 2
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> PhotoFragment()
                    1 -> CollectionsFragment()
                    else -> PhotoFragment()
                }
            }
        }
        TabLayoutMediator(binding.unSplashTabLayout, binding.unSplashViewPager) { tab, position ->
            tab.text = resources.getStringArray(R.array.un_splash_tabs)[position]
        }.attach()

        binding.unSplashViewPager.offscreenPageLimit = 2
        binding.unSplashViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            // TODO
        })
        binding.unSplashBottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_search -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                    true
                }
                R.id.action_filter -> {
                    MaterialAlertDialogBuilder(this).show()
                    true
                }
                else -> false
            }
        }

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