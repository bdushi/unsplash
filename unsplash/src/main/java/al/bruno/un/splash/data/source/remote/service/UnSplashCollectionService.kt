package al.bruno.un.splash.data.source.remote.service

import al.bruno.un.splash.model.api.Collection
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnSplashCollectionService {
    @GET("collections")
    suspend fun collections(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): Response<List<Collection>>
}