package al.bruno.adapter

import al.bruno.adapter.holder.ViewHolderAdapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.ViewDataBinding

class DropDownArrayAdapter<T : Selection, VM:ViewDataBinding, VD: ViewDataBinding>(
    context: Context,
    private val r: Int,
    private val d: Int,
    private val t: Array<T>,
    private val bindingView: (t: T, vm: VM) -> Unit,
    private val bindingDropDownView: (t: T, vm: VM) -> Unit) : ArrayAdapter<T>(context, r, t) {
//    private val themedSpinnerAdapter: ThemedSpinnerAdapter.Helper = ThemedSpinnerAdapter.Helper(context)
    private var selection = -1
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var contentView = view
        if(contentView == null) {
            contentView = LayoutInflater.from(parent.context).inflate(r, parent, false)
            val spinnerHolder = ViewHolderAdapter(contentView, bindingView)
            spinnerHolder.bind(t[position])
            contentView.tag = spinnerHolder
        } else {
            val spinnerHolder = contentView.tag as ViewHolderAdapter<T, VM>
            spinnerHolder.bind(t[position])
        }
        return contentView!!
    }

    override fun getDropDownView(position: Int, view: View?, parent: ViewGroup): View? {
        var contentView = view
        val tt:T = t[position]
        if(contentView == null) {
            contentView = LayoutInflater.from(parent.context).inflate(r, parent, false)
            val spinnerHolder = ViewHolderAdapter(contentView, bindingDropDownView)
            spinnerHolder.bind(tt)
            contentView.tag = spinnerHolder
        } else {
            val spinnerHolder = contentView.tag as ViewHolderAdapter<T, VD>
            spinnerHolder.bind(tt)
        }
        if(position == selection) {
            tt.selection(true)
        } else {
            tt.selection(false)
        }
        return contentView
    }

    override fun isEmpty(): Boolean {
        return t.isEmpty()
    }

    fun setSelection(position: Int) {
        selection = position
        notifyDataSetChanged()
    }
}