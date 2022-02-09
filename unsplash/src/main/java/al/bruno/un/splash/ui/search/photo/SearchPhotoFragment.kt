package al.bruno.un.splash.ui.search.photo

import PHOTO
import al.bruno.adapter.PagedListAdapter
import al.bruno.adapter.OnClickListener
import al.bruno.di.base.BaseFragment
import al.bruno.un.splash.R
import al.bruno.un.splash.common.collectLatestFlow
import al.bruno.un.splash.databinding.FragmentUnSplashBinding
import al.bruno.un.splash.databinding.FragmentUnSplashPhotoBinding
import al.bruno.un.splash.databinding.PhotoSingleItemBinding
import al.bruno.un.splash.model.api.Photo
import al.bruno.un.splash.ui.search.UnSplashSearchViewModel
import al.bruno.un.splash.utils.MyRxBus
import android.app.Activity
import android.content.Intent
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

class SearchPhotoFragment : BaseFragment() {
    private var _binding: FragmentUnSplashPhotoBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var myRxBusSearch: MyRxBus

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelProvider)[UnSplashSearchViewModel::class.java]
    }

    private val adapter by lazy {
        PagedListAdapter(
            R.layout.photo_single_item,
            { t: Photo, vm: PhotoSingleItemBinding ->
                vm.photo = t
                vm.onClick = object : OnClickListener<Photo> {
                    override fun onClick(t: Photo) {
                        activity?.setResult(Activity.RESULT_OK, Intent().putExtra(PHOTO, t.urls.regular))
                        activity?.finish()
                    }
                }
            },
            object : DiffUtil.ItemCallback<Photo>() {
                override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                    oldItem == newItem

                override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                    oldItem.id == newItem.id
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnSplashPhotoBinding.inflate(layoutInflater)
        binding?.adapter = adapter
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectLatestFlow(viewModel.error) { error ->
            error?.let { Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show() }
        }
        myRxBusSearch
            .getRxBus()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { search ->
                    search.query?.let { query ->
                        collectLatestFlow(
                            viewModel.searchPhotosPagedList(
                                query,
                                search.orientation
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