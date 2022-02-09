package al.bruno.un.splash.data.source.remote

import al.bruno.un.splash.data.source.UnSplashCollectionDataSource
import al.bruno.un.splash.data.source.remote.service.UnSplashCollectionService
import al.bruno.un.splash.model.api.Collection
import retrofit2.Response
import javax.inject.Inject

class UnSplashCollectionRemoteDataSource @Inject constructor(private val unSplashCollectionService: UnSplashCollectionService): UnSplashCollectionDataSource {
    override suspend fun collections(page: Int?, perPage: Int?): Response<List<Collection>> {
        return unSplashCollectionService.collections(page, perPage)
    }
}