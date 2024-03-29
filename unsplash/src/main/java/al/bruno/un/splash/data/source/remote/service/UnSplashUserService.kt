package al.bruno.un.splash.data.source.remote.service

import al.bruno.un.splash.model.OrderBy
import al.bruno.un.splash.model.Orientation
import al.bruno.un.splash.model.Resolution
import al.bruno.un.splash.data.source.model.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnSplashUserService {
    @GET("users/{username}/photos")
    suspend fun photos(
        @Path("username") username: String,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("order_by") orderBy: OrderBy,
        @Query("resolution") resolution: Resolution,
        @Query("orientation") orientation: Orientation,
        @Query("stats") stats: Boolean,
        @Query("quantity") quantity: Int
    ): Response<List<Photo>>
}