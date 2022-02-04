package al.bruno.un.splash.model.api

import com.google.gson.annotations.SerializedName

data class Collection(
    val id: String,
    val title: String,
    val description: String,
    @SerializedName("published_at") val publishedAt: String,
    @SerializedName("last_collected_at") val lastCollectedAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    val curated: Boolean,
    val featured: Boolean,
    @SerializedName("total_photos") val totalPhotos: Int,
    @SerializedName("private") val private: Boolean,
    @SerializedName("share_key") val shareKey: String,
    @SerializedName("cover_photo") val coverPhoto: Photo,
    @SerializedName("preview_photos") val previewPhotos: List<PreviewPhotos>,
    val user: User,
    val links: CollectionLinks,
    val tags: List<Tag>
)
