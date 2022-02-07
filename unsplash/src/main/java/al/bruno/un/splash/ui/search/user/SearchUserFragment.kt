package al.bruno.un.splash.ui.search.user

import al.bruno.adapter.*
import al.bruno.di.base.BaseFragment
import al.bruno.un.splash.R
import al.bruno.un.splash.adapter.ParentItemAdapter
import al.bruno.un.splash.common.collectLatestFlow
import al.bruno.un.splash.databinding.FragmentUnSplashBinding
import al.bruno.un.splash.databinding.UnSplashUserItemBinding
import al.bruno.un.splash.databinding.UsersPhotoSingleItemBinding
import al.bruno.un.splash.databinding.UsersSingleItemBinding
import al.bruno.un.splash.dto.UsersPhoto
import al.bruno.un.splash.model.api.Photo
import al.bruno.un.splash.model.api.User
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

class SearchUserFragment : BaseFragment() {
    @Inject
    lateinit var myRxBusSearch: MyRxBus

    private var _binding: FragmentUnSplashBinding? = null
    private val binding get() = _binding
    private lateinit var onClickListener: OnClickListener<Photo>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelProvider)[UnSplashSearchViewModel::class.java]
    }
    private val adapter by lazy {
        ParentItemAdapter(R.layout.un_splash_user_item)
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
        myRxBusSearch
            .getRxBus()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { search ->
                    search.query?.let { query ->
                        collectLatestFlow(
                            viewModel.searchUsers(
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