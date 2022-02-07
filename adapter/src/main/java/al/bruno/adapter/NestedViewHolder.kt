package al.bruno.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class NestedViewHolder<T, VM : ViewDataBinding>(itemView: View, private val bindingData: BindingData<T, VM>) : RecyclerView.ViewHolder(itemView) {
    private val binding: VM? = DataBindingUtil.bind(itemView)
    fun bind(t:T) {
        binding?.let {
            bindingData.bindData(t, it)
            it.executePendingBindings()
        }
    }
}