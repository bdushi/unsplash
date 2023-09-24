package al.bruno.adapter

import al.bruno.adapter.holder.CustomViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewCustomAdapter<T, VM: ViewDataBinding> (
    private val r: Int,
    private val t: List<T>?,
    private val bindingData: (t: T, vm: VM) -> Unit,) : RecyclerView.Adapter<CustomViewHolder<T, VM>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder<T, VM> {
        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(r, parent, false), bindingData)
    }

    override fun getItemCount(): Int {
        return t?.size ?: 0
    }

    override fun onBindViewHolder(holder: CustomViewHolder<T, VM>, position: Int) {
        t?.get(position)?.let { holder.bind(it) }
    }
}