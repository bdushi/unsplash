package al.bruno.un.splash.model.api

/**
 * "self": "https://api.unsplash.com/collections/296",
 * "html": "https://unsplash.com/collections/296",
 * "photos": "https://api.unsplash.com/collections/296/photos",
 * "related": "https://api.unsplash.com/collections/296/related"
 */

data class CollectionLinks(
    val self: String,
    val html: String,
    val photos: String,
    val related: String
)
