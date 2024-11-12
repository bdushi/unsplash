package al.bruno.un.splash.ui.un.splash.collections

import al.bruno.un.splash.common.Result
import al.bruno.un.splash.data.source.UnSplashCollectionRepository
import al.bruno.un.splash.data.source.model.Collection
import androidx.paging.PagingSource
import androidx.paging.PagingState

class CollectionPagingSource(
    private val unSplashCollectionRepository: UnSplashCollectionRepository
) : PagingSource<Int, Collection>() {
    override fun getRefreshKey(state: PagingState<Int, Collection>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Collection> {
        val position = params.key ?: 1
        return try {
            when (val response = unSplashCollectionRepository.collections(
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