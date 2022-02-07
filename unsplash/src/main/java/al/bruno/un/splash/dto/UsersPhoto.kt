package al.bruno.un.splash.dto

import al.bruno.un.splash.model.api.Photo
import al.bruno.un.splash.model.api.User

data class UsersPhoto(val id: String, val users: User, val photos: List<Photo>)