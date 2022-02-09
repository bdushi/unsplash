package al.bruno.un.splash.adapter

import androidx.paging.PagingDataAdapter
import al.bruno.un.splash.dto.UsersPhoto
import androidx.recyclerview.widget.DiffUtil
import android.view.ViewGroup

/**
 * https://www.geeksforgeeks.org/how-to-create-a-nested-recyclerview-in-android/
 */

class ParentItemAdapter constructor(private val resources: Int) : PagingDataAdapter<UsersPhoto, ParentViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        return ParentViewHolder.create(resources, parent)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.bind(getItem(position))
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