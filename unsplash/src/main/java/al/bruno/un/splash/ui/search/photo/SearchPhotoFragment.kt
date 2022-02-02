package al.bruno.un.splash.ui.search.photo

import al.bruno.adapter.BindingData
import al.bruno.adapter.CustomPagedListAdapter
import al.bruno.adapter.OnClickListener
import al.bruno.di.base.BaseFragment
import al.bruno.un.splash.R
import al.bruno.un.splash.databinding.FragmentUnSplashBinding
import al.bruno.un.splash.databinding.PhotoSingleItemBinding
import al.bruno.un.splash.model.api.Photo
import al.bruno.un.splash.ui.search.UnSplashSearchViewModel
import al.bruno.un.splash.utils.MyRxBus
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchPhotoFragment : BaseFragment() {
    @Inject
    lateinit var myRxBusSearch: MyRxBus
    private lateinit var onClickListener: OnClickListener<Photo>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelProvider)[UnSplashSearchViewModel::class.java]
    }

    private val adapter by lazy {
        CustomPagedListAdapter(
                R.layout.photo_single_item,
            object : BindingData<Photo, PhotoSingleItemBinding> {
                override fun bindData(t: Photo, vm: PhotoSingleItemBinding) {
                    vm.photo = t
                    vm.onClick = onClickListener
                }
            },
            object : DiffUtil.ItemCallback<Photo>() {
                override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                    oldItem == newItem

                override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                    oldItem.id == newItem.id
            })
    }

    fun setOnClickListener(onClickListener: OnClickListener<Photo>): SearchPhotoFragment {
        this.onClickListener = onClickListener
        return this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentUnSplashBinding = DataBindingUtil.inflate<FragmentUnSplashBinding>(
            inflater,
                R.layout.fragment_un_splash,
            container,
            false
        )
        fragmentUnSplashBinding.adapter = adapter
        fragmentUnSplashBinding.viewModel = viewModel
        fragmentUnSplashBinding.lifecycleOwner = this
        return fragmentUnSplashBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
        }
        myRxBusSearch
            .getRxBus()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { search ->
                    run {
                        search.query?.let { query ->
                            viewModel.searchPhotosPagedList(
                                query,
                                search.orientation
                            ).observe(viewLifecycleOwner) { adapter.submitList(it) }
                        }
                    }
                },
                { throwable ->
                    Snackbar.make(
                        view,
                        throwable.message.toString(),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            )
            .isDisposed
    }
}