package com.example.inspiration.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.annotation.RequiresApi
import com.example.inspiration.constant.Constant
import java.io.File
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @ClassName:      Ext
 * @Author:         Yan
 * @CreateDate:     12点47分
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:     //TODO
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