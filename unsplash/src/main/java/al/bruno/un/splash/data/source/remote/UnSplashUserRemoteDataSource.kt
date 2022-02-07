package al.bruno.un.splash.data.source.remote

import al.bruno.un.splash.common.OrderBy
import al.bruno.un.splash.common.Orientation
import al.bruno.un.splash.common.Resolution
import al.bruno.un.splash.data.source.UnSplashUserDataSource
import al.bruno.un.splash.data.source.remote.service.UnSplashUserService
import al.bruno.un.splash.model.api.Photo
import retrofit2.Response
import javax.inject.Inject

class UnSplashUserRemoteDataSource @Inject constructor(private val unSplashUserService: UnSplashUserService): UnSplashUserDataSource {
    override suspend fun photos(
        username: String,
        page: Int?,
        perPage: Int?,
        orderBy: OrderBy,
        resolution: Resolution,
        orientation: Orientation,
        stats: Boolean,
        quantity: Int
    ): Response<List<Photo>> {
        return unSplashUserService.photos(username, page, perPage, orderBy, resolution, orientation, stats, quantity)
    }
}