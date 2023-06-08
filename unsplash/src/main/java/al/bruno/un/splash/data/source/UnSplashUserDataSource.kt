package al.bruno.un.splash.data.source

import al.bruno.un.splash.model.OrderBy
import al.bruno.un.splash.model.Orientation
import al.bruno.un.splash.model.Resolution
import al.bruno.un.splash.data.source.model.Photo
import retrofit2.Response

interface UnSplashUserDataSource {
    suspend fun photos(
        username: String,
        page: Int?,
        perPage: Int?,
        orderBy: OrderBy = OrderBy.latest,
        resolution: Resolution = Resolution.days,
        orientation: Orientation = Orientation.all,
        stats: Boolean = false,
        quantity: Int = 30
    ): Response<List<Photo>>
}