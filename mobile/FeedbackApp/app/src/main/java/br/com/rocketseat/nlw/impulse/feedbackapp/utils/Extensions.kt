package br.com.rocketseat.nlw.impulse.feedbackapp.utils

import android.graphics.Bitmap
import android.os.Environment
import android.text.format.DateFormat
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.util.*

fun Bitmap?.saveFile(pictureFile: File): Boolean {
    try {
        val fos = FileOutputStream(pictureFile)
        this?.compress(Bitmap.CompressFormat.PNG, 0, fos)
        fos.close()
        return true
    } catch (e: Exception) {
        e.printStackTrace()
        Log.e("TAG", "saveFile: ${e.message}", e.cause)
    }
    return false
}

//fun Bitmap?.saveFile(): String? {
//    val date = Date()
//    val format: CharSequence = DateFormat.format("yyyy-MM-dd_hh:mm:ss", date)
//    try {
//        val dirpath = "${Environment.DIRECTORY_PICTURES}/Feedback App"
//        val file = File(dirpath)
//        if (!file.exists()) {
//            file.mkdir()
//        }
//
//        val path = File(dirpath,"ScreenShot_$format.png")
//        val fos = FileOutputStream(path)
//        this?.compress(Bitmap.CompressFormat.PNG, 0, fos)
//        fos.close()
//        return path.absolutePath.toString()
//    } catch (e: Exception) {
//        e.printStackTrace()
//    }
//    return null
//}