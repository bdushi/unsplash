package al.bruno.un.splash.data.source

import al.bruno.un.splash.common.Result
import al.bruno.un.splash.data.source.model.Collection
import javax.inject.Inject

class UnSplashCollectionRepository @Inject constructor(private val unSplashCollectionDataSource: UnSplashCollectionDataSource) {
    suspend fun collections(
        page: Int?, perPage: Int?
    ): Result<List<Collection>> {
        return try {
            Result.Loading<List<Collection>>()
            val response = unSplashCollectionDataSource.collections(page, perPage)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.Success(body)
            } else {
                Result.Error(response.message())
            }
        } catch (ex: Exception) {
            Result.Error(ex.message)
        }
    }
}