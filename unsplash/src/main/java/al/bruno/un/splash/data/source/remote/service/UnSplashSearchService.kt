package al.bruno.un.splash.data.source.remote.service

/**
 * Search api.
 */

import al.bruno.un.splash.data.source.model.SearchCollectionsResult
import al.bruno.un.splash.data.source.model.SearchPhotosResult
import al.bruno.un.splash.data.source.model.SearchUsersResult
import al.bruno.un.splash.model.Orientation
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnSplashSearchService {
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: CharSequence,
        @Query("orientation") orientation: Orientation,
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
