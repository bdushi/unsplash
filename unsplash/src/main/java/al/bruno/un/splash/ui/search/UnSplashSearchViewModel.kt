package al.bruno.un.splash.ui.search

import al.bruno.un.splash.data.source.UnSplashSearchRepository
import al.bruno.un.splash.model.api.Photo
import al.bruno.un.splash.model.api.SearchCollectionsResult
import al.bruno.un.splash.model.api.SearchPhotosResult
import al.bruno.un.splash.model.api.SearchUsersResult
import al.bruno.un.splash.paging.UnSplashDataSource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnSplashSearchViewModel @Inject constructor(
    private val unSplashSearchRepository: UnSplashSearchRepository
    ) : ViewModel() {
    val searchPhoto = MutableLiveData<SearchPhotosResult>()
    val searchCollection = MutableLiveData<SearchCollectionsResult>()
    val searchUser = MutableLiveData<SearchUsersResult>()

    private val _error = MutableLiveData<String>()
    val error:LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    var loading: LiveData<Boolean> = _loading

    fun searchPhotosPagedList(query: CharSequence, orientation: String?): LiveData<PagedList<Photo>> {
        return LivePagedListBuilder(
            UnSplashDataSource(unSplashSearchRepository, _error, _loading, query, orientation),
            PagedList
                .Config
                .Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(30)
                .setPageSize(30)
                /*.setPrefetchDistance(5)*/
                .build())
            //.setInitialLoadKey(1)
            .setFetchExecutor(Executors.newFixedThreadPool(5))
            .build()
    }

    fun searchCollections(query: String) {
        unSplashSearchRepository
            .searchCollections(query, 1, 30)
            .enqueue(object : Callback<SearchCollectionsResult> {
                override fun onFailure(call: Call<SearchCollectionsResult>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<SearchCollectionsResult>, response: Response<SearchCollectionsResult>) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
    }

    fun searchUsers(query: String) {
        unSplashSearchRepository
            .searchUsers(query, 1, 30)
            .enqueue(object : Callback<SearchUsersResult> {
                override fun onFailure(call: Call<SearchUsersResult>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<SearchUsersResult>, response: Response<SearchUsersResult>) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
    }
}