package al.bruno.un.splash.data.source.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * "self": "https://api.unsplash.com/photos/LBI7cgq3pbM",
 * "html": "https://unsplash.com/photos/LBI7cgq3pbM",
 * "download": "https://unsplash.com/photos/LBI7cgq3pbM/download",
 * "download_location": "https://api.unsplash.com/photos/LBI7cgq3pbM/download"
 */

@Serializable
data class PhotoLinks(
    val self: String,
    val html: String,
    val download: String,
    @SerialName("download_location")
    val downloadLocation: String
)