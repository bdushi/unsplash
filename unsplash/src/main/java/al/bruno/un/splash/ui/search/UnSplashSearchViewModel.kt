package al.bruno.un.splash.ui.search

import al.bruno.di.base.BaseViewModel
import al.bruno.un.splash.common.Result
import al.bruno.un.splash.data.source.UnSplashSearchRepository
import al.bruno.un.splash.data.source.UnSplashUserRepository
import al.bruno.un.splash.dto.UsersPhoto
import al.bruno.un.splash.model.api.Collection
import al.bruno.un.splash.model.api.Photo
import al.bruno.un.splash.ui.search.collection.paging.UnSplashSearchCollectionPagingSource
import al.bruno.un.splash.ui.search.photo.paging.UnSplashedSearchPhotoPagingSource
import al.bruno.un.splash.ui.search.user.paging.UnSplashSearchUserPagingSource
import al.bruno.un.splash.ui.search.user.paging.UnSplashUserPhotoPagingSource
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnSplashSearchViewModel @Inject constructor(
    private val unSplashSearchRepository: UnSplashSearchRepository,
    private val unSplashUserRepository: UnSplashUserRepository
) : BaseViewModel() {

    private val _photos = MutableStateFlow(listOf<Photo>())
    var photos: StateFlow<List<Photo>> = _photos

    fun searchPhotosPagedList(
        query: CharSequence,
        orientation: String?
    ): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                UnSplashedSearchPhotoPagingSource(
                    unSplashSearchRepository,
                    _error,
                    _loading,
                    query,
                    orientation
                )
            }
        ).flow
    }

    fun searchCollections(query: CharSequence): Flow<PagingData<Collection>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_BUFFER_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                UnSplashSearchCollectionPagingSource(
                    unSplashSearchRepository,
                    query
                )
            }
        ).flow
    }

    fun searchUsers(query: CharSequence): Flow<PagingData<UsersPhoto>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_BUFFER_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                UnSplashSearchUserPagingSource(
                    unSplashSearchRepository,
                    unSplashUserRepository,
                    query
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