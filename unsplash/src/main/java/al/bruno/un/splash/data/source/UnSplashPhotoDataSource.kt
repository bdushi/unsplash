package al.bruno.un.splash.data.source

import al.bruno.un.splash.data.source.model.Photo
import retrofit2.Response

interface UnSplashPhotoDataSource {
    suspend fun getPhotos(
        page: Int?,
        perPage: Int?,
        orderBy: String?
    ): Response<List<Photo>>
}