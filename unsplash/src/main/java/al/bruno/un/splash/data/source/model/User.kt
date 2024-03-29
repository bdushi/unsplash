package al.bruno.un.splash.data.source.model

import com.google.gson.annotations.SerializedName

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

data class User(
    val id: String,
    val updated_at: String,
    val username: String,
    val name: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("twitter_username") val twitterUsername: String,
    @SerializedName("portfolio_url") val portfolioUrl: String?,
    val bio: String,
    val location: String,
    val links: Links,
    @SerializedName("profile_image") val profileImage: ProfileImage,
    @SerializedName("instagram_username") val instagramUsername: String,
    @SerializedName("total_collections") val totalCollections: Int,
    @SerializedName("total_likes") val totalLikes: Int,
    @SerializedName("total_photos") val totalPhotos: Int,
    @SerializedName("followed_by_user") val followedByUser: Boolean,
    @SerializedName("followers_count") val followersCount: Int,
    @SerializedName("following_count") val followingCount: Int,
    @SerializedName("accepted_tos") val acceptedTos: Boolean,
    @SerializedName("for_hire") val forHire: Boolean,
    val downloads: Int,
    val badge: Badge?,
    val social: Social
)
