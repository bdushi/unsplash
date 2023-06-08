package al.bruno.un.splash.adapter

import al.bruno.adapter.CustomListAdapter
import al.bruno.un.splash.R
import al.bruno.un.splash.data.source.model.Photo
import al.bruno.un.splash.databinding.UnSplashUserItemBinding
import al.bruno.un.splash.databinding.UsersPhotoSingleItemBinding
import al.bruno.un.splash.model.UsersPhoto
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ParentViewHolder(
    private val unSplashUserItemBinding: UnSplashUserItemBinding
) : RecyclerView.ViewHolder(unSplashUserItemBinding.root) {
    private val recycledViewPool = RecyclerView.RecycledViewPool()
    private val pagedListAdapter = CustomListAdapter(
        R.layout.users_photo_single_item,
        { t: Photo, vm: UsersPhotoSingleItemBinding ->
            vm.photo = t
        }, object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }
        })

    fun bind(user: UsersPhoto?) {
        unSplashUserItemBinding.unSplashUserPhoto.adapter = pagedListAdapter
        unSplashUserItemBinding.unSplashUserPhoto.setRecycledViewPool(recycledViewPool)
        Picasso.get()
            .load(user?.users?.profileImage?.small)
            .into(unSplashUserItemBinding.unSplashUserProfileImage)
        unSplashUserItemBinding.unSplashUserName.text = user?.users?.name
        unSplashUserItemBinding.unSplashUserUsername.text = user?.users?.username
        pagedListAdapter.submitList(user?.photos)
    }

    companion object {
        fun create(res: Int, parent: ViewGroup): ParentViewHolder {
            return ParentViewHolder(
                UnSplashUserItemBinding.bind(
                    LayoutInflater.from(parent.context).inflate(res, parent, false)
                )
            )
        }
    }
}