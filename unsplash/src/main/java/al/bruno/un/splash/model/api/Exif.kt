package al.bruno.un.splash.model.api

/**
 * "make": "Canon",
 * "model": "Canon EOS 40D",
 * "exposure_time": "0.011111111111111112",
 * "aperture": "4.970854",
 * "focal_length": "37",
 * "iso": 100
 */

data class Exif(var make: String,
                var model: String,
                var exposure_time: String,
                var aperture: String,
                var focal_length: String,
                var iso: Int)
