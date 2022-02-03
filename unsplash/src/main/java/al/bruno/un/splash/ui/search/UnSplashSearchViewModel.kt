package al.bruno.un.splash.ui.search

import al.bruno.un.splash.common.Result
import al.bruno.un.splash.data.source.UnSplashSearchDataSource
import al.bruno.un.splash.data.source.UnSplashSearchRepository
import al.bruno.un.splash.model.api.Photo
import al.bruno.un.splash.paging.UnSplashedPagingSource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnSplashSearchViewModel @Inject constructor(
    private val unSplashSearchRepository: UnSplashSearchRepository) :
    ViewModel() {
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading

    fun searchPhotosPagedList(
        query: CharSequence,
        orientation: String?
    ): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnSplashedPagingSource(unSplashSearchRepository, _error, _loading, query, orientation) }
        ).flow
    }

    fun searchCollections(query: String) {
        viewModelScope.launch {
            when (unSplashSearchRepository.searchCollections(query, 1, 30)) {
                is Result.Error -> {
                }
                is Result.Success -> {
                }
                is Result.Loading -> {

                }
            }
        }
    }

    fun searchUsers(query: String) {
        viewModelScope.launch {
            unSplashSearchRepository
                .searchUsers(query, 1, 30)
        }
    }
}