package al.bruno.un.splash.model.api

/**
 * "self": "https://api.unsplash.com/users/crew",
 * "html": "https://unsplash.com/crew",
 * "photos": "https://api.unsplash.com/users/crew/photos",
 * "likes": "https://api.unsplash.com/users/crew/likes",
 * "portfolio": "https://api.unsplash.com/users/crew/portfolio"
 */

data class UserLinks(val self: String, val html: String, val photos: String, val likes: String, val portfolio: String)