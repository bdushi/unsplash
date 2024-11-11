package al.bruno.un.splash.data.source.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * "make": "Canon",
 * "model": "Canon EOS 40D",
 * "exposure_time": "0.011111111111111112",
 * "aperture": "4.970854",
 * "focal_length": "37",
 * "iso": 100
 */

@Serializable
data class Exif(
    var make: String,
    var model: String,
    @SerialName("exposure_time")
    var exposureTime: String,
    var aperture: String,
    @SerialName("focal_length")
    var focalLength: String,
    var iso: Int
)
