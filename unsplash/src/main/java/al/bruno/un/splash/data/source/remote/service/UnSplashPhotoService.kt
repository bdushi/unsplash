package al.bruno.un.splash.data.source.remote.service

import al.bruno.un.splash.data.source.model.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnSplashPhotoService {
    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("order_by") orderBy: String?
    ): Response<List<Photo>>
}