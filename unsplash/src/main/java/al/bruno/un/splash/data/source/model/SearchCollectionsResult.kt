package al.bruno.un.splash.data.source.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchCollectionsResult(
    val total: Int = 0,
    @SerialName("total_pages") val totalPages : Int = 0,
    val results: List<Collection>)