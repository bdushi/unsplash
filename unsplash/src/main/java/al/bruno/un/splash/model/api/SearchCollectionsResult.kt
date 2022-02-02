package al.bruno.un.splash.model.api

import com.google.gson.annotations.SerializedName


data class SearchCollectionsResult(val total: Int = 0, @SerializedName("total_pages") val totalPages : Int = 0, val results: List<al.bruno.un.splash.model.api.Collection>)