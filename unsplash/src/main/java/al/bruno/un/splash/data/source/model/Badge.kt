package al.bruno.un.splash.data.source.model

import kotlinx.serialization.Serializable

/**
 * "title": "Book contributor",
 * "primary": true,
 * "slug": "book-contributor",
 * "link": "https://book.unsplash.com"
 */
@Serializable
data class Badge(val title: String, val primary: Boolean, val slug: String, val link: String)
