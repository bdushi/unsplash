package al.bruno.un.splash.data.source.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchPhotosResult(
    /*Page number to retrieve. (Optional; default: 1)*/
    val total: Int,
    /*Number of items per page. (Optional; default: 10)*/
    @SerialName("total_pages")
    val totalPages: Int,
    val results: List<Photo>) {
    override fun toString(): String {
        return "total: $total total_pages: $totalPages"
    }
}