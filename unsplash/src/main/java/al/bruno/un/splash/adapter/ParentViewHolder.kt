package al.bruno.un.splash.adapter

import al.bruno.adapter.BindingData
import al.bruno.adapter.CustomListAdapter
import al.bruno.un.splash.R
import al.bruno.un.splash.databinding.UnSplashUserItemBinding
import al.bruno.un.splash.databinding.UsersPhotoSingleItemBinding
import al.bruno.un.splash.model.api.Photo
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val pagedListAdapter = CustomListAdapter(
        R.layout.users_photo_single_item,
        object : BindingData<Photo, UsersPhotoSingleItemBinding> {
            override fun bindData(t: Photo, vm: UsersPhotoSingleItemBinding) {
                vm.photo = t
            }
        },
        object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }
        })
    val unSplashUserItemBinding = UnSplashUserItemBinding.bind(itemView)
}