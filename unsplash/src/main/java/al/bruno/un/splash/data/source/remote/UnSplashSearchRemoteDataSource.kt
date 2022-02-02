package al.bruno.un.splash.data.source.remote

import al.bruno.un.splash.data.source.UnSplashSearchDataSource
import al.bruno.un.splash.data.source.remote.service.UnSplashSearchService
import al.bruno.un.splash.model.api.*
import javax.inject.Inject
import retrofit2.Call

class UnSplashSearchRemoteDataSource @Inject constructor(private val unSplashSearchService: UnSplashSearchService) : UnSplashSearchDataSource {
    override fun searchPhotos(query: CharSequence, orientation: String?, page: Int, perPage: Int): Call<SearchPhotosResult> {
        return unSplashSearchService.searchPhotos(query = query, page = page, perPage = perPage, orientation = orientation)
    }

    override fun searchUsers(query: CharSequence, page: Int, perPage: Int): Call<SearchUsersResult> {
        return unSplashSearchService.searchUsers(query, page, perPage)
    }

    override fun searchCollections(query: CharSequence, page: Int, perPage: Int): Call<SearchCollectionsResult> {
        return unSplashSearchService.searchCollections(query, page, perPage)
    }
}