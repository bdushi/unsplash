package al.bruno.un.splash.data.source

import al.bruno.un.splash.model.api.*
import retrofit2.Call
import retrofit2.Response

interface UnSplashSearchDataSource {
    suspend fun searchPhotos(query: CharSequence, orientation: String?, page: Int, perPage: Int): Response<SearchPhotosResult>
    suspend fun searchUsers(query: CharSequence, page: Int, perPage: Int): Response<SearchUsersResult>
    suspend fun searchCollections( query: CharSequence, page: Int, perPage: Int): Response<SearchCollectionsResult>
}