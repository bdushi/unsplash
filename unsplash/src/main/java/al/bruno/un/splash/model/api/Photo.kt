package al.bruno.un.splash.model.api

import com.google.gson.annotations.SerializedName

data class Photo(
    val id: String,
    val created_at: String,
    val updated_at: String,
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
    val urls: PhotoUrls,
    val links: PhotoLinks,
    val user: User,
    val story: Story
)