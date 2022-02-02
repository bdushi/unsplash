package al.bruno.un.splash.paging

import al.bruno.un.splash.data.source.UnSplashSearchRepository
import al.bruno.un.splash.model.api.Photo
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import java.lang.Exception

class UnSplashedKeyedDataSource(
    private val unSplashSearchRepository: UnSplashSearchRepository,
    private val error: MutableLiveData<String>,
    private val loading: MutableLiveData<Boolean>,
    private val query: CharSequence,
    private val orientation: String?) : PageKeyedDataSource<Int, Photo>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>) {
        val currentPage = 1
        val nextPage = currentPage + 1
        loading.postValue(true)
        try {
            val response = unSplashSearchRepository.searchPhotos(query = query, orientation = orientation, page = currentPage, perPage = params.requestedLoadSize).execute()
            loading.postValue(false)
            if(response.isSuccessful) {
                response.body()?.let { searchPhotosResult -> callback.onResult(searchPhotosResult.results,  currentPage, nextPage) }
            } else {
                error.postValue(response.message())
            }
        } catch (ex: Exception) {
            loading.postValue(false)
            error.postValue(ex.message)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        val currentPage = params.key
        val nextPage = currentPage + 1
        loading.postValue(true)
        try {
            val response = unSplashSearchRepository.searchPhotos(query = query, orientation = orientation, page = currentPage, perPage = params.requestedLoadSize).execute()
            loading.postValue(false)
            if(response.isSuccessful) {
                response.body()?.let { searchPhotosResult -> callback.onResult(searchPhotosResult.results, nextPage) }
            } else {
                error.postValue(response.message())
            }
        } catch (ex: Exception) {
            loading.postValue(false)
            error.postValue(ex.message)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        Log.d(UnSplashedKeyedDataSource::class.java.name, "loadBefore")
    }
}