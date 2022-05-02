package com.example.inspiration.utils

import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation

/**
 *
 * @ClassName:      Animation
 * @Author:         Yan
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:     动画的工具类
 */
/**
 * 获取旋转动画
 * @param fromDegrees X轴顺时针转动到fromDegrees为旋转的起始点，
 * @param toDegrees X轴顺时针转动到toDegrees为旋转的结束点
 */
fun getRotateAnimation(fromDegrees: Float, toDegrees: Float): Animation {
    val rotateAnimation = RotateAnimation(
        fromDegrees,
        toDegrees,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f
    )
    rotateAnimation.fillAfter = true
    rotateAnimation.duration = 2000
    rotateAnimation.repeatCount = Animation.INFINITE
    rotateAnimation.repeatMode = Animation.RESTART
    rotateAnimation.interpolator = LinearInterpolator()
    return rotateAnimation
}