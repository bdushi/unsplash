package al.bruno.un.splash.data.source.remote

import al.bruno.un.splash.data.source.UnSplashPhotoDataSource
import al.bruno.un.splash.data.source.remote.service.UnSplashPhotoService
import al.bruno.un.splash.model.api.Photo
import retrofit2.Response
import javax.inject.Inject

class UnSplashPhotoRemoteDataSource @Inject constructor(private val unSplashPhotoService: UnSplashPhotoService) :
    UnSplashPhotoDataSource {
    override suspend fun getPhotos(
        page: Int?,
        perPage: Int?,
        orderBy: String?
    ): Response<List<Photo>> {
        return unSplashPhotoService.getPhotos(page, perPage, orderBy)
    }
}