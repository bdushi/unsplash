package al.bruno.un.splash.ui.search.user

import al.bruno.adapter.LoadStateAdapter
import al.bruno.di.base.BaseFragment
import al.bruno.un.splash.R
import al.bruno.un.splash.adapter.ParentItemAdapter
import al.bruno.un.splash.common.collectLatestFlow
import al.bruno.un.splash.databinding.FragmentUnSplashPhotoBinding
import al.bruno.un.splash.databinding.LoadStateItemViewBinding
import al.bruno.un.splash.ui.search.UnSplashSearchViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

class SearchUserFragment : BaseFragment() {

    private var _binding: FragmentUnSplashPhotoBinding? = null
    private val binding get() = _binding

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelProvider)[UnSplashSearchViewModel::class.java]
    }
    private val propertiesLoadStateAdapter =
        LoadStateAdapter<LoadStateItemViewBinding>(R.layout.load_state_item_view) { loadState, vm ->
            vm.loadState = loadState
            vm.onClick = View.OnClickListener {
                adapter.retry()
            }
        }

    private val adapter by lazy {
        ParentItemAdapter(R.layout.un_splash_user_item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnSplashPhotoBinding.inflate(inflater)
        binding?.unSplash?.adapter = adapter.withLoadStateHeaderAndFooter(
            footer = propertiesLoadStateAdapter,
            header = propertiesLoadStateAdapter
        )
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectLatestFlow(
            viewModel.user
        ) {
            adapter.submitData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}