package al.bruno.un.splash.ui.search.collection

import PHOTO
import al.bruno.adapter.LoadStateAdapter
import al.bruno.adapter.OnClickListener
import al.bruno.adapter.PagedListAdapter
import al.bruno.di.base.BaseFragment
import al.bruno.un.splash.R
import al.bruno.un.splash.common.collectLatestFlow
import al.bruno.un.splash.databinding.CollectionSingleItemBinding
import al.bruno.un.splash.databinding.FragmentUnSplashPhotoBinding
import al.bruno.un.splash.ui.search.UnSplashSearchViewModel
import al.bruno.un.splash.data.source.model.Collection
import al.bruno.un.splash.databinding.LoadStateItemViewBinding
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil

class SearchCollectionFragment : BaseFragment() {

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
        PagedListAdapter(
            R.layout.collection_single_item,
            { t: Collection, vm: CollectionSingleItemBinding ->
                vm.collection = t
                vm.onClick = object : OnClickListener<Collection> {
                    override fun onClick(t: Collection) {
                        activity?.setResult(Activity.RESULT_OK, Intent().putExtra(PHOTO, t.coverPhoto.urls.regular))
                        activity?.finish()
                    }
                }
            },
            object : DiffUtil.ItemCallback<Collection>() {
                override fun areItemsTheSame(oldItem: Collection, newItem: Collection): Boolean =
                    oldItem == newItem

                override fun areContentsTheSame(oldItem: Collection, newItem: Collection): Boolean =
                    oldItem.id == newItem.id
            })
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
            viewModel.collection
        ) {
            adapter.submitData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}