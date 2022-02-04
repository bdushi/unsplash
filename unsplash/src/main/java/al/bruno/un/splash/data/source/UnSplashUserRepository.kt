package al.bruno.un.splash.data.source

import al.bruno.un.splash.common.OrderBy
import al.bruno.un.splash.common.Orientation
import al.bruno.un.splash.common.Resolution
import al.bruno.un.splash.common.Result
import al.bruno.un.splash.model.api.Photo
import al.bruno.un.splash.model.api.SearchPhotosResult
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class UnSplashUserRepository @Inject constructor(private val unSplashUserDataSource: UnSplashUserDataSource){
    suspend fun photos(
        username: String,
        page: Int,
        perPage: Int,
        orderBy: OrderBy = OrderBy.latest,
        resolution: Resolution = Resolution.days,
        orientation: Orientation = Orientation.portrait,
        stats: Boolean = false,
        quantity: Int = 30
    ): Result<List<Photo>> {
        return try {
            Result.Loading<List<Photo>>()
            val response = unSplashUserDataSource.photos(username, page, perPage, orderBy, resolution, orientation, stats, quantity)
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