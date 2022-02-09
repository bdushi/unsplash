package al.bruno.un.splash.ui.search.collection.paging

import al.bruno.un.splash.common.Result
import al.bruno.un.splash.data.source.UnSplashSearchRepository
import al.bruno.un.splash.model.api.Collection
import androidx.paging.PagingSource
import androidx.paging.PagingState

class UnSplashSearchCollectionPagingSource constructor(
    private val unSplashSearchDataSource: UnSplashSearchRepository,
    private val query: CharSequence) : PagingSource<Int, Collection>() {
    override fun getRefreshKey(state: PagingState<Int, Collection>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Collection> {
        val position = params.key ?: 1
        return try {
            when (val response = unSplashSearchDataSource.searchCollections(
                query = query,
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