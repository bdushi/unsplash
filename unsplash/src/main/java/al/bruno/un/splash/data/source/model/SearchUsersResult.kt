package al.bruno.un.splash.data.source.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchUsersResult(
    val total: Int,
    @SerialName("total_pages") val totalPages: Int,
    val results: List<User>
)