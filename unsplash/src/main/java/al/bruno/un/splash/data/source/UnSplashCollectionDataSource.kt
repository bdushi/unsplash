package al.bruno.un.splash.data.source

import al.bruno.un.splash.data.source.model.Collection
import retrofit2.Response

interface UnSplashCollectionDataSource {
    suspend fun collections(
        page: Int?, perPage: Int?
    ): Response<List<Collection>>
}