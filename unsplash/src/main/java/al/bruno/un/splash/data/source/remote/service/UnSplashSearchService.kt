package al.bruno.un.splash.data.source.remote.service


import al.bruno.un.splash.model.api.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Search api.
 */

interface UnSplashSearchService {
    @GET("search/photos")
    fun searchPhotos(
        @Query("query") query: CharSequence,
        @Query("orientation") orientation: String?,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<SearchPhotosResult>

    @GET("search/users")
    fun searchUsers(
        @Query("query") query: CharSequence,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<SearchUsersResult>

    @GET("search/collections")
    fun searchCollections(
        @Query("query") query: CharSequence,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<SearchCollectionsResult>
}
