package br.com.rocketseat.nlw.impulse.feedbackapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Feedback(
    val type: String,
    val comment: String,
    @SerializedName("screenshot") val screenShot: String? = null,
    @SerializedName("screenshotbase64") val screenShotBase64: String? = null

) : Serializable
