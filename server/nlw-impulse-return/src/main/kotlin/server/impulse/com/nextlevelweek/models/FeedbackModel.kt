package server.impulse.com.nextlevelweek.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedbackModel(
    val id: String? = null,
    val type: String,
    val comment: String,
    @SerialName("screenshot") val screenShot: String? = null,
    @SerialName("screenshotbase64") val screenShotBase64: String? = null
)