package al.bruno.un.splash.model.api

import com.google.gson.annotations.SerializedName

/**
 * "instagram_username": "mometrixtestpreparation",
 * "portfolio_url": http://mometrix.com,
 * "twitter_username": "Mometrix",
 * "paypal_email": null
 */

data class Social(
    @SerializedName("instagram_username") val instagramUsername: String,
    @SerializedName("portfolio_url") val portfolioUrl: String,
    @SerializedName("twitter_username") val twitterUsername: String,
    @SerializedName("paypal_email") val paypalEmail: String
)