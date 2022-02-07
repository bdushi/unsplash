package al.bruno.un.splash.binding.utils

import al.bruno.un.splash.model.api.Collection
import al.bruno.un.splash.model.api.Photo
import al.bruno.un.splash.model.api.ProfileImage

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

object Adapter {
    @JvmStatic
    @BindingAdapter("bind:image")
    fun imageProvider(imageView: AppCompatImageView, photo: Photo) {
//        val finalHeight =
//            (imageView.resources.displayMetrics.widthPixels * 2) / (photo.width.toFloat() / photo.height.toFloat())
//        imageView.minimumHeight = finalHeight.toInt()
        Picasso
            .get()
            .load(photo.urls.regular)
            .resize(60, 123)
            .centerCrop()
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("bind:image")
    fun imageProvider(imageView: AppCompatImageView, photo: Collection) {
        val finalHeight =
            (imageView.resources.displayMetrics.widthPixels * 2) / (photo.coverPhoto.width.toFloat() / photo.coverPhoto.height.toFloat())
        imageView.minimumHeight = finalHeight.toInt()
        Picasso
            .get()
            .load(photo.coverPhoto.urls.regular)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter(value = ["bind:image"], requireAll = false)
    fun imageProvider(
        photoProfile: ShapeableImageView,
        profileImage: ProfileImage
    ) {
        Picasso
            .get()
            .load(profileImage.large)
            .into(photoProfile)
    }
}
