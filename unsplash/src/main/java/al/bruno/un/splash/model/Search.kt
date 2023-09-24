package al.bruno.un.splash.model

data class Search(var query: CharSequence, var orientation: Orientation = Orientation.landscape) {
    override fun toString(): String {
        return "$query $orientation"
    }
}