package al.bruno.un.splash.ui.un.splash

import al.bruno.di.base.BaseFragment
import al.bruno.un.splash.R
import al.bruno.un.splash.databinding.FragmentUnSplashBinding
import al.bruno.un.splash.ui.search.UnSplashSearchFragment
import al.bruno.un.splash.ui.un.splash.collections.CollectionsFragment
import al.bruno.un.splash.ui.un.splash.photo.PhotoFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator

class UnSplashFragment : BaseFragment() {
    lateinit var binding: FragmentUnSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUnSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.un_splash_root, UnSplashSearchFragment(), "TAG")
                        .addToBackStack("TAG")
                        .commit()
                    true
                }
                R.id.action_filter -> {
                    MaterialAlertDialogBuilder(requireContext()).show()
                    true
                }
                else ->
                    false
            }
        }
    }
}