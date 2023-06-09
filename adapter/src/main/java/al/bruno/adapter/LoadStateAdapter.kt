package al.bruno.adapter

import al.bruno.adapter.holder.LoadViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class LoadStateAdapter<VM: ViewDataBinding>(
    private val r: Int,
    private val bindingData: (loadState: LoadState, vm: VM) -> Unit
) : LoadStateAdapter<LoadViewHolder<VM>>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadViewHolder<VM> {
        return LoadViewHolder(LayoutInflater.from(parent.context).inflate(r, parent, false), bindingData)
    }

    override fun onBindViewHolder(holder: LoadViewHolder<VM>, loadState: LoadState) {
        holder.bind(loadState)
    }
}
