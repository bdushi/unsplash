package al.bruno.un.splash.data.source.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Collection(
    val id: String,
    val title: String,
    val description: String,
    @SerialName("published_at") val publishedAt: String,
    @SerialName("last_collected_at") val lastCollectedAt: String,
    @SerialName("updated_at") val updatedAt: String,
    val curated: Boolean,
    val featured: Boolean,
    @SerialName("total_photos") val totalPhotos: Int,
    @SerialName("private") val private: Boolean,
    @SerialName("share_key") val shareKey: String,
    @SerialName("cover_photo") val coverPhoto: Photo,
    @SerialName("preview_photos") val previewPhotos: List<PreviewPhotos>,
    val user: User,
    val links: CollectionLinks,
    val tags: List<Tag>
)
