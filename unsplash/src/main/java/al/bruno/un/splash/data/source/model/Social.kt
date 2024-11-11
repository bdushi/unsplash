package al.bruno.un.splash.data.source.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * "instagram_username": "mometrixtestpreparation",
 * "portfolio_url": http://mometrix.com,
 * "twitter_username": "Mometrix",
 * "paypal_email": null
 */

@Serializable
data class Social(
    @SerialName("instagram_username")
    val instagramUsername: String,
    @SerialName("portfolio_url")
    val portfolioUrl: String,
    @SerialName("twitter_username")
    val twitterUsername: String,
    @SerialName("paypal_email")
    val paypalEmail: String?
)