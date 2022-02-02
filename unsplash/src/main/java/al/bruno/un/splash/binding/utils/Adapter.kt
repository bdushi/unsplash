package al.bruno.un.splash.binding.utils

import al.bruno.un.splash.model.api.Collection
import al.bruno.un.splash.model.api.Photo
import al.bruno.un.splash.model.api.ProfileImage
import android.net.Uri
import android.widget.ImageView

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

object Adapter {
    /*@BindingAdapter(value = {"bind:xMargin", "bind:drawableSwipeLeft", "bind:drawableSwipeRight", "bind:colorSwipeLeft", "bind:colorSwipeRight", "bind:onLeftSwipeItemListener", "bind:onRightSwipeItemListener"}, requireAll = false)
    public static void swipeItem(RecyclerView recyclerView, float xMargin, Drawable drawableSwipeLeft, Drawable drawableSwipeRight, int colorSwipeLeft, int colorSwipeRight, OnSwipeItemListener onLeftSwipeItemListener, OnSwipeItemListener onRightSwipeItemListener) {
        new ItemTouchHelper(new SimpleItemTouchHelper
                .Builder()
                .setXMarkMargin(xMargin)
                .setLeftToRightColor(colorSwipeRight)
                .setLeftToRightIcon(drawableSwipeRight)

                .setRightToLeftColor(colorSwipeLeft)
                .setRightToLeftIcon(drawableSwipeLeft)
                .build()
                .onLeftSwipeItemListener(onLeftSwipeItemListener)
                .onRightSwipeItemListener(onRightSwipeItemListener))
                .attachToRecyclerView(recyclerView);
    }*/

    @JvmStatic
    @BindingAdapter("bind:image")
    fun imageProvider(imageView: AppCompatImageView, photo: Photo) {
        val finalHeight =
            (imageView.resources.displayMetrics.widthPixels * 2) / (photo.width.toFloat() / photo.height.toFloat())
        imageView.minimumHeight = finalHeight.toInt()
        Picasso.get().load(photo.urls.regular).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("bind:image")
    fun imageProvider(imageView: AppCompatImageView, photo: Collection) {
        val finalHeight =
            (imageView.resources.displayMetrics.widthPixels * 2) / (photo.coverPhoto.width.toFloat() / photo.coverPhoto.height.toFloat())
        imageView.minimumHeight = finalHeight.toInt()
        Picasso.get().load(photo.coverPhoto.urls.regular).into(imageView)
    }

    @JvmStatic
    @BindingAdapter(value = ["bind:image"], requireAll = false)
    fun imageProvider(
        photoProfile: ShapeableImageView,
        profileImage: ProfileImage
    ) {
        Picasso.get()
            .load(profileImage.large)
            .into(photoProfile)
    }
}
