package al.bruno.un.splash.ui.search.photo.paging

import al.bruno.un.splash.common.Orientation
import al.bruno.un.splash.common.Result
import al.bruno.un.splash.data.source.UnSplashSearchRepository
import al.bruno.un.splash.model.api.Photo
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.StateFlow

class UnSplashedSearchPhotoPagingSource(
    private val unSplashSearchDataSource: UnSplashSearchRepository,
    private val error: StateFlow<String?>,
    private val loading: StateFlow<Boolean>,
    private val query: CharSequence,
    private val orientation: Orientation
) : PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: 1
        return try {
            when (val response = unSplashSearchDataSource.searchPhotos(
                query = query,
                orientation = orientation,
                page = position,
                perPage = params.loadSize
            )) {
                is Result.Error -> {
                    LoadResult.Error(Throwable(response.exception))
                }
                is Result.Success -> {
                    LoadResult.Page(
                        data = response.data.results,
                        prevKey = if (position == 1) null else position - 1,
                        nextKey = if (response.data.results.isEmpty()) null else position + 1
                    )
                }
                else -> {
                    LoadResult.Error(Throwable())
                }
            }

        } catch (ex: Exception) {
            return LoadResult.Error(ex)
        }
    }
}
