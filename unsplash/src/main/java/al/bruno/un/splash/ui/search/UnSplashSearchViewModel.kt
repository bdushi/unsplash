package al.bruno.un.splash.ui.search

import al.bruno.di.base.BaseViewModel
import al.bruno.un.splash.common.Result
import al.bruno.un.splash.data.source.UnSplashSearchRepository
import al.bruno.un.splash.data.source.UnSplashUserRepository
import al.bruno.un.splash.data.source.model.Photo
import al.bruno.un.splash.model.Search
import al.bruno.un.splash.ui.search.collection.paging.UnSplashSearchCollectionPagingSource
import al.bruno.un.splash.ui.search.photo.paging.UnSplashedSearchPhotoPagingSource
import al.bruno.un.splash.ui.search.user.paging.UnSplashSearchUserPagingSource
import al.bruno.un.splash.ui.search.user.paging.UnSplashUserPhotoPagingSource
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalCoroutinesApi::class)
class UnSplashSearchViewModel @Inject constructor(
    private val unSplashSearchRepository: UnSplashSearchRepository,
    private val unSplashUserRepository: UnSplashUserRepository
) : BaseViewModel() {

    private val _photos = MutableStateFlow(listOf<Photo>())
    var photos: StateFlow<List<Photo>> = _photos

    val search = MutableStateFlow<Search?>(null)

    val result = search
        .filterNotNull()
        .flatMapLatest {
            Pager(
                config = PagingConfig(
                    pageSize = DEFAULT_BUFFER_SIZE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    UnSplashedSearchPhotoPagingSource(
                        unSplashSearchRepository,
                        _error,
                        _loading,
                        it
                    )
                }
            ).flow
        }

    val collection = search
        .filterNotNull()
        .flatMapLatest {
            Pager(
                config = PagingConfig(
                    pageSize = DEFAULT_BUFFER_SIZE,
                    enablePlaceholders = true
                ),
                pagingSourceFactory = {
                    UnSplashSearchCollectionPagingSource(
                        unSplashSearchRepository,
                        it.query
                    )
                }
            ).flow
        }


    val user = search
        .filterNotNull()
        .flatMapLatest {
            Pager(
                config = PagingConfig(
                    pageSize = DEFAULT_BUFFER_SIZE,
                    enablePlaceholders = true
                ),
                pagingSourceFactory = {
                    UnSplashSearchUserPagingSource(
                        unSplashSearchRepository,
                        unSplashUserRepository,
                        it.query
                    )
                }
            ).flow
        }

    fun photo(username: String): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_BUFFER_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                UnSplashUserPhotoPagingSource(
                    unSplashUserRepository,
                    username
                )
            }
        ).flow
    }

    fun photos(username: String) {
        viewModelScope.launch {
            try {
                when (val response = unSplashUserRepository.photos(
                    username = username,
                    page = 1,
                    perPage = 3
                )) {
                    is Result.Error -> {
                        _loading.value = false
                        _error.value = response.exception
                    }

                    is Result.Success -> {
                        _loading.value = false
                        _photos.value = response.data

                    }

                    is Result.Loading -> {
                        _loading.value = true
                    }
                }

            } catch (ex: Exception) {
                _loading.value = false
                _error.value = ex.message
            }
        }
    }
}