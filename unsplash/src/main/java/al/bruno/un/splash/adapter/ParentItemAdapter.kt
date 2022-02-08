package al.bruno.un.splash.adapter

import androidx.paging.PagingDataAdapter
import al.bruno.un.splash.dto.UsersPhoto
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import android.view.ViewGroup
import android.view.LayoutInflater
import com.squareup.picasso.Picasso

/**
 * https://www.geeksforgeeks.org/how-to-create-a-nested-recyclerview-in-android/
 */

class ParentItemAdapter constructor(private val resources: Int) : PagingDataAdapter<UsersPhoto, ParentViewHolder>(diffUtil) {
    private val viewPool = RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        return ParentViewHolder(LayoutInflater.from(parent.context).inflate(resources, parent, false))
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val user = getItem(position)
        holder.unSplashUserItemBinding.unSplashUserPhoto.adapter = holder.pagedListAdapter
        holder.unSplashUserItemBinding.unSplashUserPhoto
            .setRecycledViewPool(viewPool)
        Picasso.get()
            .load(user?.users?.profileImage?.small)
            .into(holder.unSplashUserItemBinding.unSplashUserProfileImage)
        holder.unSplashUserItemBinding.unSplashUserName.text = user?.users?.name
        holder.unSplashUserItemBinding.unSplashUserUsername.text = user?.users?.username
        holder.pagedListAdapter.submitList(user?.photos)
    }

    companion object {
        private val diffUtil: DiffUtil.ItemCallback<UsersPhoto> =
            object : DiffUtil.ItemCallback<UsersPhoto>() {
                override fun areItemsTheSame(oldItem: UsersPhoto, newItem: UsersPhoto): Boolean {
                    return oldItem === newItem
                }

                override fun areContentsTheSame(oldItem: UsersPhoto, newItem: UsersPhoto): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}