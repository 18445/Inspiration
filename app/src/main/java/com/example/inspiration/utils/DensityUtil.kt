package com.example.inspiration.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 *
 * @ClassName:      DensityUtil
 * @Author:         Yan
 * @CreateDate:     12点47分
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:     关于像素的工具类
 */


val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

inline val Double.dp: Float
    get() = run {
    return toFloat().dp
}

inline val Int.dp: Float
    get() = run {
    return toFloat().dp
}

