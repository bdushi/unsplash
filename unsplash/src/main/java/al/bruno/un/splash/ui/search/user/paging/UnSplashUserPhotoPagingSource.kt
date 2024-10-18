package al.bruno.un.splash.ui.search.user.paging

import al.bruno.un.splash.common.Result
import al.bruno.un.splash.data.source.UnSplashUserRepository
import al.bruno.un.splash.data.source.model.Photo
import androidx.paging.PagingSource
import androidx.paging.PagingState

class UnSplashUserPhotoPagingSource(
    private val unSplashUserRepository: UnSplashUserRepository,
    private val username: String): PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: 1
        return try {
            when (val response = unSplashUserRepository.photos(
                username = username,
                page = position,
                perPage = params.loadSize
            )) {
                is Result.Error -> {
                    LoadResult.Error(Throwable(response.exception))
                }
                is Result.Success -> {
                    LoadResult.Page(
                        data = response.data,
                        prevKey = if (position == 1) null else position - 1,
                        nextKey = if (response.data.isEmpty()) null else position + 1
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