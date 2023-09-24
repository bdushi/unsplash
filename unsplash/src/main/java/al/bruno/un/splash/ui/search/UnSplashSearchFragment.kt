package al.bruno.un.splash.ui.search

import al.bruno.di.base.BaseFragment
import al.bruno.un.splash.R
import al.bruno.un.splash.databinding.FragmentUnSplashSearchBinding
import al.bruno.un.splash.model.Search
import al.bruno.un.splash.ui.search.collection.SearchCollectionFragment
import al.bruno.un.splash.ui.search.photo.SearchPhotoFragment
import al.bruno.un.splash.ui.search.user.SearchUserFragment
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator

class UnSplashSearchFragment : BaseFragment() {
    private val unsplash =
        arrayListOf(SearchPhotoFragment(), SearchCollectionFragment(), SearchUserFragment())
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelProvider)[UnSplashSearchViewModel::class.java]
    }
    private var _binding: FragmentUnSplashSearchBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnSplashSearchBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.unSplashViewPager?.isUserInputEnabled = false
        binding?.unSplashViewPager?.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = unsplash.size
            override fun createFragment(position: Int) = unsplash[position]
        }

        binding?.unSplashToolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        TabLayoutMediator(
            binding!!.unSplashTabLayout,
            binding!!.unSplashViewPager
        ) { tab, position ->
            tab.text = resources.getStringArray(R.array.un_splash_tabs)[position]
        }.attach()

        binding?.unSplashTextInput?.setOnEditorActionListener { textView: TextView, i: Int, _: KeyEvent? ->
            if (i == KeyEvent.ACTION_DOWN /*&& keyEvent?.keyCode == KeyEvent.KEYCODE_SEARCH*/) {
                viewModel.search.value = Search(textView.text)
                activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        binding?.unSplashTextInput?.isFocusable = true
        binding?.unSplashTextInput?.requestFocus()

        if (binding?.unSplashTextInput?.requestFocus() == true && binding?.unSplashTextInput?.text?.toString() == "") {
            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}