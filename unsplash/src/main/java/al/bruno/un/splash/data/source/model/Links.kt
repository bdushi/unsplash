package al.bruno.un.splash.data.source.model

/**
 * "self": "https://api.unsplash.com/users/crew",
 * "html": "https://unsplash.com/crew",
 * "photos": "https://api.unsplash.com/users/crew/photos",
 * "likes": "https://api.unsplash.com/users/crew/likes",
 * "portfolio": "https://api.unsplash.com/users/crew/portfolio"
 * "following": "https://api.unsplash.com/users/mometrixtestprep/following",
 * "followers": "https://api.unsplash.com/users/mometrixtestprep/followers"
 */

data class Links(
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String,
    val following: String,
    val followers: String
)