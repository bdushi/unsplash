package al.bruno.adapter.holder

import al.bruno.adapter.BindingData
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

class ViewHolderAdapter<T, VM: ViewDataBinding>(view: View, private val bindingData: BindingData<T, VM>) {
    private val binding: VM? = DataBindingUtil.bind(view)

    fun bind(t:T) {
        binding?.let {
            bindingData.bindData(t, it)
            it.executePendingBindings()
        }
    }
}