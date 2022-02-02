package al.bruno.un.splash.data.source

import al.bruno.un.splash.model.api.*
import retrofit2.Call

interface UnSplashSearchDataSource {
    fun searchPhotos(query: CharSequence, orientation: String?, page: Int, perPage: Int): Call<SearchPhotosResult>
    fun searchUsers(query: CharSequence, page: Int, perPage: Int): Call<SearchUsersResult>
    fun searchCollections( query: CharSequence, page: Int, perPage: Int): Call<SearchCollectionsResult>
}