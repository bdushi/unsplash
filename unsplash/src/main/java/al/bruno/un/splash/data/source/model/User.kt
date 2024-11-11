package al.bruno.un.splash.data.source.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * "id": "pXhwzz1JtQU",
 * "updated_at": "2016-07-10T11:00:01-05:00",
 * "username": "jimmyexample",
 * "name": "James Example",
 * "first_name": "James",
 * "last_name": "Example",
 * "instagram_username": "instantgrammer",
 * "twitter_username": "jimmy",
 * "portfolio_url": null,
 * "bio": "The user's bio",
 * "location": "Montreal, Qc",
 * "total_likes": 20,
 * "total_photos": 10,
 * "total_collections": 5,
 * "followed_by_user": false,
 * "followers_count": 300,
 * "following_count": 25,
 * "downloads": 225974,
 * "profile_image": {
 * "small": "https://images.unsplash.com/face-springmorning.jpg?q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32",
 * "medium": "https://images.unsplash.com/face-springmorning.jpg?q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64",
 * "large": "https://images.unsplash.com/face-springmorning.jpg?q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128"
 * },
 * "badge": {
 * "title": "Book contributor",
 * "primary": true,
 * "slug": "book-contributor",
 * "link": "https://book.unsplash.com"
 * },
 * "links": {
 * "self": "https://api.unsplash.com/users/jimmyexample",
 * "html": "https://unsplash.com/jimmyexample",
 * "photos": "https://api.unsplash.com/users/jimmyexample/photos",
 * "likes": "https://api.unsplash.com/users/jimmyexample/likes",
 * "portfolio": "https://api.unsplash.com/users/jimmyexample/portfolio"
 * }
 */

@Serializable
data class User(
    val id: String,
    @SerialName("updated_at")
    val updatedAt: String,
    val username: String,
    val name: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("twitter_username")
    val twitterUsername: String,
    @SerialName("portfolio_url")
    val portfolioUrl: String?,
    val bio: String,
    val location: String,
    val links: Links,
    @SerialName("profile_image") val
    profileImage: ProfileImage,
    @SerialName("instagram_username")
    val instagramUsername: String,
    @SerialName("total_collections")
    val totalCollections: Int,
    @SerialName("total_likes")
    val totalLikes: Int,
    @SerialName("total_photos")
    val totalPhotos: Int,
    @SerialName("followed_by_user")
    val followedByUser: Boolean,
    @SerialName("followers_count")
    val followersCount: Int,
    @SerialName("following_count")
    val followingCount: Int,
    @SerialName("accepted_tos")
    val acceptedTos: Boolean,
    @SerialName("for_hire")
    val forHire: Boolean,
    val downloads: Int,
    val badge: Badge?,
    val social: Social
)
