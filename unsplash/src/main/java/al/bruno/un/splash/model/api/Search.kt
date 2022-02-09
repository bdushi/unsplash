package al.bruno.un.splash.model.api

import al.bruno.un.splash.common.Orientation

data class Search(var query:CharSequence?, var orientation:Orientation = Orientation.landscape) {
    override fun toString(): String {
        return "$query $orientation"
    }
}