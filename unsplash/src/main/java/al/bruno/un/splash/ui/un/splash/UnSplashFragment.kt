package al.bruno.un.splash.ui.un.splash

import al.bruno.di.base.BaseFragment
import al.bruno.un.splash.R
import al.bruno.un.splash.databinding.FragmentUnSplashBinding
import al.bruno.un.splash.ui.un.splash.collections.CollectionsFragment
import al.bruno.un.splash.ui.un.splash.photo.PhotoFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator

class UnSplashFragment : BaseFragment() {
    lateinit var binding: FragmentUnSplashBinding
    private val unsplash = arrayListOf(PhotoFragment(), CollectionsFragment())
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
            override fun getItemCount() = unsplash.size
            override fun createFragment(position: Int) = unsplash[position]
        }
        TabLayoutMediator(binding.unSplashTabLayout, binding.unSplashViewPager) { tab, position ->
            tab.text = resources.getStringArray(R.array.un_splash_tabs)[position]
        }.attach()

        binding.unSplashBottomAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_search -> {
                    findNavController().navigate(R.id.action_unSplashFragment_to_unSplashSearchFragment)
                    true
                }
                R.id.action_filter -> {
                    MaterialAlertDialogBuilder(
                        requireContext()
                    ).show()
                    true
                }
                else ->
                    false
            }
        }
    }
}