package al.bruno.un.splash.epoxy

import al.bruno.adapter.RecyclerViewCustomAdapter
import al.bruno.un.splash.R
import al.bruno.un.splash.UserPhotoItemBindingModel_
import al.bruno.un.splash.databinding.UnSplashPhotoItemBinding
import al.bruno.un.splash.model.Photo
import al.bruno.un.splash.model.User
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.CarouselModelBuilder
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController

class UserPhotoController : PagingDataEpoxyController<User>() {
    @AutoModel
    lateinit var header: UserPhotoItemBindingModel_
    override fun buildItemModel(currentPosition: Int, item: User?): EpoxyModel<*> {
        return header
            .id(item?.id)
            .user(item)
            .adapter(RecyclerViewCustomAdapter<Photo, UnSplashPhotoItemBinding>(
                R.layout.un_splash_photo_item, item?.photos
            ) { t, vm ->
                vm.photo = t
            })

    }

}

inline fun <T> CarouselModelBuilder.withModelsFrom(
    items: List<T>, modelBuilder: (T) -> EpoxyModel<*>
) {
    models(items.map { modelBuilder(it) })
}

inline fun EpoxyController.carousel(modelInitializer: CarouselModelBuilder.() -> Unit) {
    CarouselModel_().apply {
        modelInitializer()
    }.addTo(this)
}