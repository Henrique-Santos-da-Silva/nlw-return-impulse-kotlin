package br.com.rocketseat.nlw.impulse.feedbackapp.ui.ui_utils

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedbackCardItems(
    @DrawableRes val image: Int,
    val title: String
): Parcelable
