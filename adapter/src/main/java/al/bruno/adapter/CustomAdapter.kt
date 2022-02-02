package al.bruno.adapter

import al.bruno.adapter.holder.CustomViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter<T, VM: ViewDataBinding>(private val t:List<T>, private val r: Int, private val bindingData: BindingData<T, VM>): RecyclerView.Adapter<CustomViewHolder<T, VM>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder<T, VM> {
        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(r, parent, false), bindingData)
    }

    override fun getItemCount(): Int {
        return t.size
    }

    override fun onBindViewHolder(viewHolder: CustomViewHolder<T, VM>, position: Int) {
        viewHolder.bind(t[position])
    }

    fun item(position: Int) : T {
        return t[position]
    }

    public val items : List<T>

    get() {
        return t
    }
}