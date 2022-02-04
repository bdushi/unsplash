package al.bruno.un.splash.data.source

import al.bruno.un.splash.common.Result
import al.bruno.un.splash.model.api.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class UnSplashSearchRepository @Inject constructor(private val unSplashSearchDataSource: UnSplashSearchDataSource) {
    suspend fun searchPhotos(query: CharSequence, orientation: String?, page: Int, perPage: Int) : Result<SearchPhotosResult> {
        return try {
            Result.Loading<SearchPhotosResult>()
            val response = unSplashSearchDataSource.searchPhotos(query = query, orientation = orientation, page = page, perPage = perPage)
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

    suspend fun searchUsers(query: CharSequence, page: Int, perPage: Int): Result<SearchUsersResult> {
        return try {
            Result.Loading<SearchUsersResult>()
            val response = unSplashSearchDataSource.searchUsers(query = query, page = page, perPage = perPage)
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

    suspend fun searchCollections(query: CharSequence, page: Int, perPage: Int): Result<SearchCollectionsResult> {
        return try {
            Result.Loading<SearchCollectionsResult>()
            val response = unSplashSearchDataSource.searchCollections(query = query, page = page, perPage = perPage)
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