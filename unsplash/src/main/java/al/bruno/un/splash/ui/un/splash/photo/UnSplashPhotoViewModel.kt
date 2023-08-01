package al.bruno.un.splash.ui.un.splash.photo

import al.bruno.di.base.BaseViewModel
import al.bruno.un.splash.data.source.UnSplashPhotoRepository
import al.bruno.un.splash.model.Photo
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnSplashPhotoViewModel @Inject constructor(private var unSplashPhotoRepository: UnSplashPhotoRepository) :
    BaseViewModel() {
    fun photosPagedList(
        orderBy: String?
    ): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_BUFFER_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                PhotoPagingSource(
                    unSplashPhotoRepository = unSplashPhotoRepository,
                    orderBy = orderBy
                )
            }
        ).flow
    }
}