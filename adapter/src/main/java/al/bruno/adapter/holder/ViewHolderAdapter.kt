package al.bruno.adapter.holder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

class ViewHolderAdapter<T, VM: ViewDataBinding>(view: View, private val bindingData: (t: T, vm: VM) -> Unit) {
    private val binding: VM? = DataBindingUtil.bind(view)

    fun bind(t:T) {
        binding?.let {
            bindingData.invoke(t, it)
            it.executePendingBindings()
        }
    }
}