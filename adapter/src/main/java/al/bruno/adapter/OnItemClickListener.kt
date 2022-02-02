package al.bruno.adapter

interface OnItemClickListener<T> {
    fun onItemClick(t: T)
    fun onLongItemClick(t: T): Boolean
}