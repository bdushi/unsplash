package al.bruno.un.splash.ui.search.user.paging

import al.bruno.un.splash.common.Result
import al.bruno.un.splash.data.source.UnSplashSearchRepository
import al.bruno.un.splash.data.source.UnSplashUserRepository
import al.bruno.un.splash.dto.UsersPhoto
import androidx.paging.PagingSource
import androidx.paging.PagingState

class UnSplashSearchUserPagingSource constructor(
    private val unSplashSearchRepository: UnSplashSearchRepository,
    private val unSplashUserRepository: UnSplashUserRepository,
    private val query: CharSequence) : PagingSource<Int, UsersPhoto>() {
    override fun getRefreshKey(state: PagingState<Int, UsersPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UsersPhoto> {
        val position = params.key ?: 1
        return try {
            when (val response = unSplashSearchRepository.searchUsers(
                query = query,
                page = position,
                perPage = params.loadSize
            )) {
                is Result.Error -> {
                    LoadResult.Error(Throwable(response.exception))
                }
                is Result.Success -> {
                    LoadResult.Page(
                        data = response.data.results.map {
                            when(val usersPhoto = unSplashUserRepository.photos(it.username, 1, 10)) {
                                is Result.Error -> {
                                    UsersPhoto(it.id, it, listOf())
                                }
                                is Result.Success -> {
                                    UsersPhoto(id = it.id, users = it, usersPhoto.data)
                                } else -> {
                                    UsersPhoto(it.id, it, listOf())
                                }
                            }
                        },
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