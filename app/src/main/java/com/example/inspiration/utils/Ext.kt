package com.example.inspiration.utils

import android.R.attr.bitmap
import android.R.attr.dialogTitle
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.net.Uri
import android.os.Environment
import android.os.StrictMode
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.inspiration.base.BaseApplication
import java.io.*
import java.util.*


/**
 *
 * @ClassName:      Ext
 * @Author:         Yan
 * @CreateDate:     12点47分
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    扩展方法
 */



/**
 * 防止View的多次点击操作
 */
inline fun View.onSafeClick(crossinline onTap: () -> Unit,duration : Long = 1500L){
    if(!this.isClickable){
        return
    }
    var lastClick=0L
    setOnClickListener {
        val gap = System.currentTimeMillis() - lastClick
        lastClick=System.currentTimeMillis()
        if(gap < duration) return@setOnClickListener
        onTap.invoke()
    }
}

fun Activity.screenshot(view: View) {
    //截图
    val resources: Resources = this.resources
    val dm: DisplayMetrics = resources.displayMetrics

    val width = dm.widthPixels
    val height = dm.heightPixels

    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

    val canvas = Canvas(bitmap)
    view.draw(canvas)

    //保存图片
    var osTemp : FileOutputStream? = null
    val cacheFile: File? = BaseApplication.instance.externalCacheDir
    val sdCardPath = cacheFile?.path
//    val sdCardPath = filesDir

    try {
        // 图片文件路径
        val imagePath = "$sdCardPath/sharePic.png"
        Log.d("imagePath",imagePath)
        val file = File(imagePath)
        val os = FileOutputStream(file)
        osTemp = os
        bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, os)
        os.flush()
    } catch (e: Exception) {
        Log.e("screenShorError",e.toString())
    }finally {
        osTemp?.close()
    }

}

fun Activity.shareImg(view: View){

    val sm = StrictMode.VmPolicy.Builder()
    StrictMode.setVmPolicy(sm.build())
    sm.detectFileUriExposure()

    //截图
    val resources: Resources = this.resources
    val dm: DisplayMetrics = resources.displayMetrics

    val width = dm.widthPixels
    val height = dm.heightPixels

//    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

    val canvas = Canvas(bitmap)

    view.draw(canvas)

//    val bm = getLocalBitmap(imagePath)

    val uri = Uri.parse(
        MediaStore.Images.Media.insertImage(
            contentResolver, bitmap, null, null
        )
    )

    val intent = Intent(Intent.ACTION_SEND) // 启动分享发送的属性
    intent.putExtra(Intent.EXTRA_STREAM, uri) // 分享的内容
    intent.type = "image/*" // 分享发送的数据类型
    val chooser = Intent.createChooser(intent, "分享图片")

    //查看能不能分享
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(chooser)
    }

}



/** * 将图片存到本地  */
private fun saveBitmap(bm: Bitmap, picName: String): Uri? {
    try {
        val dir =
            Environment.getExternalStorageDirectory().absolutePath + "/renji/" + picName + ".jpg"
        val f = File(dir)
        if (!f.exists()) {
            f.parentFile.mkdirs()
            f.createNewFile()
        }
        val out = FileOutputStream(f)
        bm.compress(Bitmap.CompressFormat.PNG, 90, out)
        out.flush()
        out.close()
        return Uri.fromFile(f)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}

fun saveBimap(bitmap: Bitmap) {
    val path: String = BaseApplication.instance.externalCacheDir.toString() + File.separator +"share010.png"
    Log.d("imagePathName",path)
    val file = File(path)
    if (!file.exists()) {
        try {
            file.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    try {
        val out = FileOutputStream(file)
        if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
            out.flush()
            out.close()
        }
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}


/**
 * 截取当前窗体的截图
 * 原理是获取当前窗体decorView的缓存生成图片
 */
fun captureWindow(activity: Activity): Bitmap? {
    // 获取当前窗体的View对象
    val view = activity.window.decorView
    view.isDrawingCacheEnabled = true

    // 生成缓存
    view.buildDrawingCache()

    val bitmap = Bitmap.createBitmap(view.drawingCache, 0, 0, view.measuredWidth, view.measuredHeight)

    view.isDrawingCacheEnabled = false
    view.destroyDrawingCache()

    return bitmap
}


fun getLocalBitmap(url: String?): Bitmap? {
    return if (url != null) {
        var fis: FileInputStream? = null
        try {
            fis = FileInputStream(url)
            BitmapFactory.decodeStream(fis) // /把流转化为Bitmap图片
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            null
        } finally {
            if (fis != null) {
                try {
                    fis.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                fis = null
            }
        }
    } else {
        null
    }
}

