package al.bruno.un.splash.data.source.model

import com.google.gson.annotations.SerializedName

data class SearchUsersResult(
    val total: Int,
    @SerializedName("total_pages") val total_pages: Int,
    val results: List<User>
)