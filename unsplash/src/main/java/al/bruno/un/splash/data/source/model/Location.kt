package al.bruno.un.splash.data.source.model

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

data class Location(val title: String,
                    val name: String,
                    val city: String,
                    val country: String,
                    val position: Position)
