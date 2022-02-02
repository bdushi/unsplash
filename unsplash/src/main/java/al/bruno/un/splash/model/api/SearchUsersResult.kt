package al.bruno.un.splash.model.api

import com.google.gson.annotations.SerializedName

data class SearchUsersResult(val total: Int, @SerializedName("total_pages") val total_pages: Int, val results: List<al.bruno.un.splash.model.api.User>)