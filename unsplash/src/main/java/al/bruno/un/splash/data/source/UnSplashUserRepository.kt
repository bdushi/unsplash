package al.bruno.un.splash.data.source

import al.bruno.un.splash.model.OrderBy
import al.bruno.un.splash.model.Orientation
import al.bruno.un.splash.model.Resolution
import al.bruno.un.splash.common.Result
import al.bruno.un.splash.data.source.model.Photo
import java.lang.Exception
import javax.inject.Inject

class UnSplashUserRepository @Inject constructor(private val unSplashUserDataSource: UnSplashUserDataSource){
    suspend fun photos(
        username: String,
        page: Int?,
        perPage: Int?,
        orderBy: OrderBy = OrderBy.latest,
        resolution: Resolution = Resolution.days,
        orientation: Orientation = Orientation.all,
        stats: Boolean = false,
        quantity: Int = 10
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