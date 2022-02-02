package al.bruno.un.splash.paging

import al.bruno.un.splash.data.source.UnSplashSearchRepository
import al.bruno.un.splash.model.api.Photo
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class UnSplashDataSource(
    private val unSplashSearchRepository: UnSplashSearchRepository,
    private val error: MutableLiveData<String>,
    private val loading: MutableLiveData<Boolean>,
    private val query: CharSequence,
    private val orientation: String?) : DataSource.Factory<Int, Photo>() {
    private val _mutableDataSource = MutableLiveData<UnSplashedKeyedDataSource>()
    private val mutableDataSource:MutableLiveData<UnSplashedKeyedDataSource>
        get() {
           return _mutableDataSource
        }

    override fun create(): DataSource<Int, Photo> {
        val unSplashedKeyedDataSource = UnSplashedKeyedDataSource(unSplashSearchRepository, error, loading, query, orientation)
        mutableDataSource.postValue(unSplashedKeyedDataSource)
        return unSplashedKeyedDataSource
    }
}