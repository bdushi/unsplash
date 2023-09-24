package al.bruno.un.splash.ui.search.photo.paging

import al.bruno.un.splash.common.Result
import al.bruno.un.splash.data.source.UnSplashSearchRepository
import al.bruno.un.splash.model.Photo
import al.bruno.un.splash.model.Search
import al.bruno.un.splash.model.Urls
import androidx.paging.PagingSource
import androidx.paging.PagingState

class UnSplashedSearchPhotoPagingSource(
    private val unSplashSearchRepository: UnSplashSearchRepository,
    private val search: Search
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
            when (val response = unSplashSearchRepository.searchPhotos(
                query = search.query,
                orientation = search.orientation,
                page = position,
                perPage = params.loadSize
            )) {
                is Result.Error -> {
                    LoadResult.Error(Throwable(response.exception))
                }
                is Result.Success -> {
                    LoadResult.Page(
                        data = response.data.results.map { photo -> Photo(photo.id, Urls(photo.urls.raw, photo.urls.full, photo.urls.regular, photo.urls.small, photo.urls.thumb), photo.width.toFloat(), photo.width.toFloat()) },
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
