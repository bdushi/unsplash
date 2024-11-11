package al.bruno.un.splash.data.source.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val id: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    val width: Int,
    val height: Int,
    val color: String,
    @SerialName("blur_hash")
    val blurHash: String,
    val downloads: Int,
    val likes: Int,
    @SerialName("liked_by_user")
    val likedByUser: Boolean,
    val description: String,
    val exif: Exif,
    val location: Location,
    @SerialName("current_user_collections")
    val currentUserCollections: List<Collection>,
    val urls: Urls,
    val links: PhotoLinks,
    val user: User,
    val story: Story
)