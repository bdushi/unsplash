package al.bruno.un.splash.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: String?) : Result<Nothing>()
    class Loading<T> : Result<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading"
        }
    }
}
suspend fun <T> Response<T>.asResponse(): Flow<Result<T>> = flow {
    val body = this@asResponse.body()
    if(this@asResponse.isSuccessful && body != null) {
        emit(Result.Success<T>(body))
    } else {
        emit(Result.Error(this@asResponse.message()))
    }
}.catch {
    emit(Result.Error(it.message))
}