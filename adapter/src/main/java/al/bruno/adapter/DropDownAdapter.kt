package al.bruno.adapter

/**
 * https://developer.android.com/guide/topics/graphics/drawable-animation
 */

import al.bruno.adapter.holder.ViewHolderAdapter
import android.view.LayoutInflater

import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.ViewDataBinding

class DropDownAdapter<T : Selection?, VM : ViewDataBinding>(
    private val resource: Int,
    private val t: Array<T>,
    private val bindingInterface: BindingData<T, VM>
) : BaseAdapter(), Filterable {
    private var selection = -1
    override fun getView(position: Int, view: View?, parent: ViewGroup): View? {
        var contentView = view
        if (contentView == null) {
//            val themedSpinnerAdapter = ThemedSpinnerAdapter.Helper(parent.context)
//            contentView = themedSpinnerAdapter.dropDownViewInflater.inflate(resource, parent, false)
            contentView = LayoutInflater.from(parent.context).inflate(resource, parent, false)
            val spinnerViewHolder = ViewHolderAdapter(contentView, bindingInterface)
            spinnerViewHolder.bind(t[position])
            contentView.tag = spinnerViewHolder
        } else {
            val spinnerViewHolder = contentView.tag as ViewHolderAdapter<T, VM>
            spinnerViewHolder.bind(t[position])
        }
        /*val tt: T = t[position]
        if (position == selection) {
            tt?.selection(true)
        } else {
            tt?.selection(false)
        }*/
        return contentView
    }

    override fun getCount(): Int {
        return t.size
    }

    override fun getItem(i: Int): T {
        return t[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    fun setSelection(position: Int) {
        selection = position
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                return FilterResults()
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
            }
        }
    }

}