package al.bruno.un.splash.model.api

import com.google.gson.annotations.SerializedName

data class PreviewPhotos(
    val id: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("blur_hash") val blurHash: String,
    val urls: Urls,
)