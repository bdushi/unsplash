package al.bruno.un.splash.data.source

import al.bruno.un.splash.data.source.model.SearchCollectionsResult
import al.bruno.un.splash.data.source.model.SearchPhotosResult
import al.bruno.un.splash.data.source.model.SearchUsersResult
import al.bruno.un.splash.model.Orientation
import retrofit2.Response

interface UnSplashSearchDataSource {
    suspend fun searchPhotos(query: CharSequence, orientation: Orientation, page: Int, perPage: Int): Response<SearchPhotosResult>
    suspend fun searchUsers(query: CharSequence, page: Int, perPage: Int): Response<SearchUsersResult>
    suspend fun searchCollections( query: CharSequence, page: Int, perPage: Int): Response<SearchCollectionsResult>
}