package al.bruno.un.splash.data.source.model

import kotlinx.serialization.Serializable

/**
 * "title": "Kitsuné Café, Montreal, Canada"
 * "name": "Kitsuné Café"
 * "city": "Montreal",
 * "country": "Canada",
 * "position": {
 * "latitude": 45.4732984,
 * "longitude": -73.6384879
 * }
 */

@Serializable
data class Location(
    val title: String,
    val name: String,
    val city: String,
    val country: String,
    val position: Position
)
