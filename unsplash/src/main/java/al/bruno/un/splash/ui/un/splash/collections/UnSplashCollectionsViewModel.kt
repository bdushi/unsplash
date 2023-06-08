package al.bruno.un.splash.ui.un.splash.collections

import al.bruno.di.base.BaseViewModel
import al.bruno.un.splash.data.source.UnSplashCollectionRepository
import al.bruno.un.splash.data.source.model.Collection
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnSplashCollectionsViewModel @Inject constructor(private var unSplashCollectionRepository: UnSplashCollectionRepository):
    BaseViewModel() {
    fun collectionPagedList(): Flow<PagingData<Collection>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_BUFFER_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                CollectionPagingSource(
                    unSplashCollectionRepository = unSplashCollectionRepository
                )
            }
        ).flow
    }
}