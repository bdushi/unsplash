package al.bruno.un.splash.data.source

import al.bruno.un.splash.common.Result
import al.bruno.un.splash.data.source.model.Photo
import javax.inject.Inject

class UnSplashPhotoRepository @Inject constructor(private val unSplashPhotoDataSource: UnSplashPhotoDataSource) {
    suspend fun getPhotos(
        page: Int?,
        perPage: Int?,
        orderBy: String?
    ): Result<List<Photo>> {
        return try {
            Result.Loading<List<Photo>>()
            val response = unSplashPhotoDataSource.getPhotos(page, perPage, orderBy)
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