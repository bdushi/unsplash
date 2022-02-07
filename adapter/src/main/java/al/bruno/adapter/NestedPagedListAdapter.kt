package al.bruno.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

class NestedPagedListAdapter<T : Any, VM: ViewDataBinding>(
    private val r: Int,
    private val bindingData: BindingData<T, VM>,
    diffUtil: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, NestedViewHolder<T, VM>>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedViewHolder<T, VM> {
        return NestedViewHolder(LayoutInflater.from(parent.context).inflate(r, parent, false), bindingData)
    }

    override fun onBindViewHolder(holder: NestedViewHolder<T, VM>, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}