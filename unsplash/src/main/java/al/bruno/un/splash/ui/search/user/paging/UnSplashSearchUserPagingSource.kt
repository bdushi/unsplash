package al.bruno.un.splash.ui.search.user.paging

import al.bruno.un.splash.common.Result
import al.bruno.un.splash.data.source.UnSplashSearchRepository
import al.bruno.un.splash.data.source.UnSplashUserRepository
import al.bruno.un.splash.model.Photo
import al.bruno.un.splash.model.Urls
import al.bruno.un.splash.model.User
import androidx.paging.PagingSource
import androidx.paging.PagingState

class UnSplashSearchUserPagingSource(
    private val unSplashSearchRepository: UnSplashSearchRepository,
    private val unSplashUserRepository: UnSplashUserRepository,
    private val query: CharSequence
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
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
                        data = response.data.results.map { user ->
                            when (val usersPhoto =
                                unSplashUserRepository.photos(user.username, 1, 10)) {
                                is Result.Error -> {
                                    User(user.id, user.profileImage.small, user.username, user.name, listOf())
                                }

                                is Result.Success -> {
                                    User(user.id, user.profileImage.small, user.username, user.name, usersPhoto.data.map { photo -> Photo(photo.id, Urls(photo.urls.raw, photo.urls.full, photo.urls.regular, photo.urls.small, photo.urls.thumb), photo.width.toFloat(), photo.width.toFloat()) })
                                }
                                else -> {
                                    User(user.id, user.profileImage.small, user.username, user.name , listOf())
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