package al.bruno.un.splash.ui.search.collection

import al.bruno.adapter.BindingData
import al.bruno.adapter.CustomPagedListAdapter
import al.bruno.di.base.BaseFragment
import al.bruno.un.splash.R
import al.bruno.un.splash.common.collectLatestFlow
import al.bruno.un.splash.databinding.CollectionSingleItemBinding
import al.bruno.un.splash.databinding.FragmentUnSplashBinding
import al.bruno.un.splash.model.api.Collection
import al.bruno.un.splash.ui.search.UnSplashSearchViewModel
import al.bruno.un.splash.utils.MyRxBus
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchCollectionFragment : BaseFragment() {
    @Inject
    lateinit var myRxBusSearch: MyRxBus

    private var _binding: FragmentUnSplashBinding? = null
    private val binding get() = _binding
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelProvider)[UnSplashSearchViewModel::class.java]
    }

    private val adapter by lazy {
        CustomPagedListAdapter(
            R.layout.collection_single_item,
            object : BindingData<Collection, CollectionSingleItemBinding> {
                override fun bindData(t: Collection, vm: CollectionSingleItemBinding) {
                    vm.collection = t
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
        _binding = FragmentUnSplashBinding.inflate(inflater)
        binding?.adapter = adapter
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myRxBusSearch
            .getRxBus()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { search ->
                    search.query?.let { query ->
                        collectLatestFlow(
                            viewModel.searchCollections(
                                query
                            )
                        ) {
                            adapter.submitData(it)
                        }
                    }
                }, { throwable ->
                    Snackbar.make(
                        view,
                        throwable.message.toString(),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            )
            .isDisposed
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}