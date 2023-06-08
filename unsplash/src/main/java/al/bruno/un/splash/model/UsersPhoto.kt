package al.bruno.un.splash.model

import al.bruno.un.splash.data.source.model.Photo
import al.bruno.un.splash.data.source.model.User


data class UsersPhoto(val id: String, val users: User, val photos: List<Photo>)