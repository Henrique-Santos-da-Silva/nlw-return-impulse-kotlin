package br.com.rocketseat.nlw.impulse.feedbackapp.utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.text.format.DateFormat
import android.util.Log
import android.view.PixelCopy
import android.view.View
import java.io.File
import java.io.FileNotFoundException
import java.util.*

object PixelCopyScreenShotUtils {

    fun getScreenShotFromView(view: View, activity: Activity, listener: (Bitmap) -> Unit) {
        activity.window?.let { window ->
            val bitmap: Bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val locationOfViewInWindow = IntArray(2)
            view.getLocationInWindow(locationOfViewInWindow)

            val xCoordinate: Int = locationOfViewInWindow[0]
            val yCoordinate: Int = locationOfViewInWindow[1]

            val scope = Rect(xCoordinate, yCoordinate, xCoordinate + view.width, yCoordinate + view.height)

            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    PixelCopy.request(window, scope, bitmap, { copyRequest ->
                        if (copyRequest == PixelCopy.SUCCESS) {
                            listener(bitmap)
                        }
                    }, Handler(Looper.myLooper()!!))

                }
            } catch (e: IllegalArgumentException) {
                Log.e(TAG, "getScreenShotFromView: ${e.message}", e.cause)
                e.printStackTrace()
            }
        }
    }

    @Deprecated("Deprecated on android versions below 8.0(Oreo)|Sdk(26)")
    fun getScreenShot(view: View): Bitmap {
        val screenView: View = view.rootView
        screenView.isDrawingCacheEnabled = true
        val bitmap: Bitmap = Bitmap.createBitmap(screenView.drawingCache)
        screenView.isDrawingCacheEnabled = false
        return bitmap
    }

    fun createSaveDirectory(): File? {
        val date = Date()
        val format: CharSequence = DateFormat.format("yyyy-MM-dd-HH:mm:ss", date)

        try {
            val dirpath = File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).absolutePath}/Feedback/")
            if (!dirpath.exists()) {
                dirpath.mkdir()
            }

            return File(dirpath, "ScreenShot-$format.png")

        } catch (io: FileNotFoundException) {
            io.printStackTrace()
        } catch (e: Exception) {
            Log.e(TAG, "createSaveDirectory: ${e.message}", e.cause)
            e.printStackTrace()
        }

        return null
    }
}