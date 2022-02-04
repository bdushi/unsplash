package al.bruno.un.splash.ui.un.splash.photo

import al.bruno.adapter.BindingData
import al.bruno.adapter.CustomPagedListAdapter
import al.bruno.di.base.BaseFragment
import al.bruno.un.splash.R
import al.bruno.un.splash.common.collectLatestFlow
import al.bruno.un.splash.databinding.FragmentUnSplashBinding
import al.bruno.un.splash.databinding.PhotoSingleItemBinding
import al.bruno.un.splash.model.api.Photo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil

class PhotoFragment : BaseFragment() {
    private var _binding: FragmentUnSplashBinding? = null
    private val binding get() = _binding

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelProvider)[UnSplashPhotoViewModel::class.java]
    }

    private val adapter by lazy {
        CustomPagedListAdapter(
            R.layout.photo_single_item,
            object : BindingData<Photo, PhotoSingleItemBinding> {
                override fun bindData(t: Photo, vm: PhotoSingleItemBinding) {
                    vm.photo = t
                }
            },
            object : DiffUtil.ItemCallback<Photo>() {
                override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                    oldItem == newItem

                override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                    oldItem.id == newItem.id
            })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentUnSplashBinding.inflate(inflater)
        binding?.adapter = adapter
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectLatestFlow(viewModel.photosPagedList(null)) {
            adapter.submitData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}