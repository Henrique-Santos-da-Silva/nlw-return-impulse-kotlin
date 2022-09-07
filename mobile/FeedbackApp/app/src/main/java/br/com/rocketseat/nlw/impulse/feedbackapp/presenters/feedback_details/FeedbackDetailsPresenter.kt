package br.com.rocketseat.nlw.impulse.feedbackapp.presenters.feedback_details

import android.app.Activity
import android.graphics.Bitmap
import android.view.View
import br.com.rocketseat.nlw.impulse.feedbackapp.contracts.FeedbackApplicationContract
import br.com.rocketseat.nlw.impulse.feedbackapp.models.Feedback
import br.com.rocketseat.nlw.impulse.feedbackapp.repositories.FeedbackRepository
import br.com.rocketseat.nlw.impulse.feedbackapp.utils.PixelCopyScreenShotUtils
import br.com.rocketseat.nlw.impulse.feedbackapp.utils.saveFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class FeedbackDetailsPresenter(private val feedbackRepository: FeedbackRepository, private val screenShotUtils: PixelCopyScreenShotUtils, private val contractView: FeedbackApplicationContract.View) : FeedbackApplicationContract.Presenter {
    override fun sendFeedback(feedback: Feedback) {
        CoroutineScope(Dispatchers.IO).launch {
            feedbackRepository.sendFeedback(feedback)
        }
    }

    override fun feedbackPixelCopyScreenshot(root: View, activity: Activity): File {
        val file: File? = screenShotUtils.createSaveDirectory()
        screenShotUtils.getScreenShotFromView(root, activity) { bitmap ->
            file?.let {
                bitmap.saveFile(it)
            }

            contractView.setImageView(bitmap)
        }

        return file!!
    }

    override fun feedbackDrawingCacheScreenShot(view: View): File {
        val bitmap: Bitmap = screenShotUtils.getScreenShot(view)
        val file: File? = screenShotUtils.createSaveDirectory()
        file?.let {
            bitmap.saveFile(it)
        }

        contractView.setImageView(bitmap)

        return file!!
    }
}