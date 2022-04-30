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
 * 将日期转换为具体时间.
 */
fun String.dayToHourTime(): String {
    val arr1 = this.split("T")
    val arr2 = arr1[1].split("+")
    return arr2[0]
}

/**
 * 格式化当前日期
 */
fun formatCurrentDate(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    return sdf.format(Date())
}

/**
 * String 转 Calendar
 */
fun String.stringToCalendar(): Calendar {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val date = sdf.parse(this)
    val calendar = Calendar.getInstance()
    if (date != null) {
        calendar.time = date
    }
    return calendar
}

@RequiresApi(Build.VERSION_CODES.N)
fun Double.getNoMoreThanTwoDigits(): String {
    val format = DecimalFormat("0.##")
    //未保留小数的舍弃规则，RoundingMode.FLOOR表示直接舍弃。
    format.roundingMode = RoundingMode.FLOOR
    return format.format(this)
}

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