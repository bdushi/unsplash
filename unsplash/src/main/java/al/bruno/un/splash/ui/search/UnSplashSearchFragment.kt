package al.bruno.un.splash.ui.search

import al.bruno.di.base.BaseFragment
import al.bruno.un.splash.R
import al.bruno.un.splash.databinding.FragmentUnSplashSearchBinding
import al.bruno.un.splash.model.api.Search
import al.bruno.un.splash.ui.search.collection.SearchCollectionFragment
import al.bruno.un.splash.ui.search.photo.SearchPhotoFragment
import al.bruno.un.splash.ui.search.user.SearchUserFragment
import al.bruno.un.splash.utils.MyRxBus
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class UnSplashSearchFragment: BaseFragment() {

    @Inject
    lateinit var myRxBusSearch: MyRxBus

    private val search = Search(null)
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
        binding?.unSplashToolbar?.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
        TabLayoutMediator(
            binding!!.unSplashTabLayout,
            binding!!.unSplashViewPager
        ) { tab, position ->
            tab.text = resources.getStringArray(R.array.un_splash_tabs)[position]
        }.attach()

        binding?.unSplashViewPager?.offscreenPageLimit = 2
        binding?.unSplashViewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            // TODO
        })

        binding!!.unSplashTextInput.setOnEditorActionListener { textView: TextView, i: Int, keyEvent: KeyEvent? ->
            if (i == KeyEvent.ACTION_DOWN /*&& keyEvent?.keyCode == KeyEvent.KEYCODE_SEARCH*/) {
                search.query = textView.text
                myRxBusSearch.setRxBus(search)
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