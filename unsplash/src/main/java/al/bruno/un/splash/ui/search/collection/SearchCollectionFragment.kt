package al.bruno.un.splash.ui.search.collection

import al.bruno.di.base.BaseFragment
import al.bruno.un.splash.R
import al.bruno.un.splash.databinding.FragmentUnSplashBinding
import al.bruno.un.splash.ui.search.UnSplashSearchViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

class SearchCollectionFragment : BaseFragment() {
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelProvider)[UnSplashSearchViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentUnSplashBinding = DataBindingUtil.inflate<FragmentUnSplashBinding>(inflater, R.layout.fragment_un_splash, container, false)
        return fragmentUnSplashBinding.root
    }
}