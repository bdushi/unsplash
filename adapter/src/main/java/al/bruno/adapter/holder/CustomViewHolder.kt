package al.bruno.adapter.holder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class CustomViewHolder<T, VM : ViewDataBinding>(itemView: View, private val bindingData: (t: T, vm: VM) -> Unit) : RecyclerView.ViewHolder(itemView) {
    private val binding: VM? = DataBindingUtil.bind(itemView)
    fun bind(t:T) {
        binding?.let {
            bindingData.invoke(t, it)
            it.executePendingBindings()
        }
    }
}