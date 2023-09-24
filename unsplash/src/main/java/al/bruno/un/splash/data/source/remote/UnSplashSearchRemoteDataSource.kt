package al.bruno.un.splash.data.source.remote

import al.bruno.un.splash.model.Orientation
import al.bruno.un.splash.data.source.UnSplashSearchDataSource
import al.bruno.un.splash.data.source.remote.service.UnSplashSearchService
import al.bruno.un.splash.data.source.model.*
import javax.inject.Inject
import retrofit2.Response

class UnSplashSearchRemoteDataSource @Inject constructor(private val unSplashSearchService: UnSplashSearchService) : UnSplashSearchDataSource {
    override suspend fun searchPhotos(query: CharSequence, orientation: Orientation, page: Int, perPage: Int): Response<SearchPhotosResult> {
        return unSplashSearchService.searchPhotos(query = query, page = page, perPage = perPage, orientation = orientation)
    }

    override suspend fun searchUsers(query: CharSequence, page: Int, perPage: Int): Response<SearchUsersResult> {
        return unSplashSearchService.searchUsers(query, page, perPage)
    }

    override suspend fun searchCollections(query: CharSequence, page: Int, perPage: Int): Response<SearchCollectionsResult> {
        return unSplashSearchService.searchCollections(query, page, perPage)
    }
}