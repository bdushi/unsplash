package al.bruno.un.splash.model

data class User(val id: String, val profileUrl:String, val username: String, val name: String, val photos: List<Photo>)

data class Photo(val id: String, val urls: Urls, val width: Float, val height: Float)

data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)