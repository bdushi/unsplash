package al.bruno.adapter.holder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView

class LoadViewHolder <VM : ViewDataBinding>(itemView: View, private val bindingData: (loadState: LoadState, vm: VM) -> Unit) : RecyclerView.ViewHolder(itemView) {
    private val binding: VM? = DataBindingUtil.bind(itemView)
    fun bind(loadState: LoadState) {
        binding?.let {
            bindingData.invoke(loadState, it)
            it.executePendingBindings()
        }
    }
}