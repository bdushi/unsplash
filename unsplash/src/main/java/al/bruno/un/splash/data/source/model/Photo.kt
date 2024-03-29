package al.bruno.un.splash.data.source.model

import com.google.gson.annotations.SerializedName

data class Photo(
    val id: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    val width: Int,
    val height: Int,
    val color: String,
    @SerializedName("blur_hash")  val blurHash: String,
    val downloads: Int,
    val likes: Int,
    @SerializedName("liked_by_user") val likedByUser: Boolean,
    val description: String,
    val exif: Exif,
    val location: Location,
    @SerializedName("current_user_collections") val currentUserCollections: List<Collection>,
    val urls: Urls,
    val links: PhotoLinks,
    val user: User,
    val story: Story
)