package al.bruno.un.splash.model.api

data class Search(var query:CharSequence?, var orientation:String?) {
    override fun toString(): String {
        return "$query $orientation"
    }
}