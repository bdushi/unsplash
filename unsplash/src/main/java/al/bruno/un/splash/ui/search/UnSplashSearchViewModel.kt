package al.bruno.un.splash.ui.search

import al.bruno.di.base.BaseViewModel
import al.bruno.un.splash.data.source.UnSplashSearchRepository
import al.bruno.un.splash.data.source.UnSplashUserRepository
import al.bruno.un.splash.model.api.Collection
import al.bruno.un.splash.model.api.Photo
import al.bruno.un.splash.model.api.User
import al.bruno.un.splash.ui.search.collection.paging.UnSplashSearchCollectionPagingSource
import al.bruno.un.splash.ui.search.photo.paging.UnSplashedSearchPhotoPagingSource
import al.bruno.un.splash.ui.search.user.paging.UnSplashUserPhotoPagingSource
import al.bruno.un.splash.ui.search.user.paging.UnSplashSearchUserPagingSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnSplashSearchViewModel @Inject constructor(
    private val unSplashSearchRepository: UnSplashSearchRepository,
    private val unSplashUserRepository: UnSplashUserRepository
) : BaseViewModel() {
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
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnSplashSearchCollectionPagingSource(unSplashSearchRepository, query) }
        ).flow
    }

    fun searchUsers(query: CharSequence): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnSplashSearchUserPagingSource(unSplashSearchRepository, query) }
        ).flow
    }

    fun photo(username: String): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnSplashUserPhotoPagingSource(unSplashUserRepository, username) }
        ).flow
    }
}