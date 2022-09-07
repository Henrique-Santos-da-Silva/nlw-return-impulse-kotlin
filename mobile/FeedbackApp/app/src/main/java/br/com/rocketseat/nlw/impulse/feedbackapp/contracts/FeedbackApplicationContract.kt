package br.com.rocketseat.nlw.impulse.feedbackapp.contracts

import android.app.Activity
import android.graphics.Bitmap
import br.com.rocketseat.nlw.impulse.feedbackapp.models.Feedback
import java.io.File

interface FeedbackApplicationContract {
    interface View {
        fun setImageView(bitmap: Bitmap)
    }

    interface Presenter {
        fun sendFeedback(feedback: Feedback)

        fun feedbackPixelCopyScreenshot(root: android.view.View, activity: Activity): File

        fun feedbackDrawingCacheScreenShot(view: android.view.View): File
    }
}