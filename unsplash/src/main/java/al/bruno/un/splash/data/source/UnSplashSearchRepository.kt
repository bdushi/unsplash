package al.bruno.un.splash.data.source


import al.bruno.un.splash.model.api.*
import androidx.paging.PageKeyedDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UnSplashSearchRepository @Inject constructor(private val unSplashSearchDataSource: UnSplashSearchDataSource) {
    fun searchPhotos(query: CharSequence, orientation: String?, page: Int, perPage: Int): Call<SearchPhotosResult> {
        return unSplashSearchDataSource.searchPhotos(query = query, orientation = orientation, page = page, perPage = perPage)
    }

    fun searchUsers(query: CharSequence, page: Int, perPage: Int): Call<SearchUsersResult> {
        return unSplashSearchDataSource.searchUsers(query = query, page = page, perPage = perPage)
    }

    fun searchCollections(query: CharSequence, page: Int, perPage: Int): Call<SearchCollectionsResult> {
        return unSplashSearchDataSource.searchCollections(query = query, page = page, perPage = perPage)
    }

    fun load(query: CharSequence, orientation: String?, page: Int, perPage: Int, callback: PageKeyedDataSource.LoadCallback<Int, Photo>) {
        unSplashSearchDataSource.searchPhotos(query = query, orientation = orientation, page = page, perPage = perPage)
            .enqueue(object : Callback<SearchPhotosResult> {
                override fun onResponse(call: Call<SearchPhotosResult>, response: Response<SearchPhotosResult>) {
                    response.body()?.let { searchPhotosResult -> searchPhotosResult.results.let { callback.onResult(it, searchPhotosResult.total)
                    }
                }
            }

            override fun onFailure(call: Call<SearchPhotosResult>, t: Throwable) {
            }
        })
    }

}