package al.bruno.un.splash.adapter

import al.bruno.un.splash.model.User
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import android.view.ViewGroup

/**
 * https://www.geeksforgeeks.org/how-to-create-a-nested-recyclerview-in-android/
 */

class ParentItemAdapter constructor(private val resources: Int) : PagingDataAdapter<User, ParentViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        return ParentViewHolder.create(resources, parent)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffUtil: DiffUtil.ItemCallback<User> =
            object : DiffUtil.ItemCallback<User>() {
                override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                    return oldItem === newItem
                }

                override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}