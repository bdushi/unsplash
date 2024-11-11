package al.bruno.un.splash.data.source.model

import kotlinx.serialization.Serializable

/**
 * title : frozen
 * url : https://images.unsplash.com/photo-1420466721261-818d807296a1
 */

@Serializable
data class Tag(val title: String, val url: String)
