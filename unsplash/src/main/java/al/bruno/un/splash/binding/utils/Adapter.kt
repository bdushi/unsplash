package al.bruno.un.splash.binding.utils

/**
 * https://futurestud.io/tutorials/picasso-image-resizing-scaling-and-fit
 *
 * https://betterprogramming.pub/aspect-ratio-with-constraint-layout-with-constraintdimensionratio-d55828ec4aae
 */

import al.bruno.un.splash.data.source.model.Collection
import al.bruno.un.splash.data.source.model.ProfileImage
import al.bruno.un.splash.model.Photo
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso


object Adapter {

    @JvmStatic
    @BindingAdapter("bind:small")
    fun imageSmallProvider(imageView: ShapeableImageView, photo: Photo) {
        val finalHeight =
            (imageView.resources.displayMetrics.widthPixels * 2) / (photo.width / photo.height)
        imageView.minimumHeight = finalHeight.toInt()
        Picasso
            .get()
            .load(photo.urls.small)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("bind:image")
    fun imageProvider(imageView: AppCompatImageView, photo: Photo) {
        val finalHeight =
            (imageView.resources.displayMetrics.widthPixels * 2) / (photo.width / photo.height)
        imageView.minimumHeight = finalHeight.toInt()
        Picasso
            .get()
            .load(photo.urls.regular)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("bind:image")
    fun imageSmallProvider(imageView: ShapeableImageView, url: String) {
        Picasso
            .get()
            .load(url)
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
