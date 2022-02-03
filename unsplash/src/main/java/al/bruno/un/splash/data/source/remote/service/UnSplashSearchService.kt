package al.bruno.un.splash.data.source.remote.service


import al.bruno.un.splash.model.api.SearchCollectionsResult
import al.bruno.un.splash.model.api.SearchPhotosResult
import al.bruno.un.splash.model.api.SearchUsersResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Search api.
 */

interface UnSplashSearchService {
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: CharSequence,
        @Query("orientation") orientation: String?,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<SearchPhotosResult>

    @GET("search/users")
    suspend fun searchUsers(
        @Query("query") query: CharSequence,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<SearchUsersResult>

    @GET("search/collections")
    suspend fun searchCollections(
        @Query("query") query: CharSequence,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<SearchCollectionsResult>
}
