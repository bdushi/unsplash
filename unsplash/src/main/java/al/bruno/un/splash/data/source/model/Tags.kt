package al.bruno.un.splash.data.source.model

import kotlinx.serialization.Serializable

@Serializable
data class Tags(val custom: List<Tag>, val aggregated: List<Tag>)