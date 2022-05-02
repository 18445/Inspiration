package com.example.inspiration.ui.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.widget
 * @ClassName:      CircleShader
 * @Author:         Yan
 * @CreateDate:     2022年05月01日 14:55:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:
 */
class CircleShader constructor(
    context: Context,
    attrs: AttributeSet?,
) : View(context, attrs) {

    //线性着色器
    private lateinit var mLinearGradient : LinearGradient

    private val mPaint = Paint()

    private var colorArray = intArrayOf()

    private var isCircle = true



    init {
        mPaint.isAntiAlias = true
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircle(canvas)
    }
    /**
     * 画圆
     */
    private fun drawCircle(canvas: Canvas){
        setLinearGradient(colorArray)
        if (isCircle){
            canvas.drawCircle(width/2f,height/2f, width/2f ,mPaint)
        }else{
            canvas.drawRect(0f,0f,width.toFloat(),height.toFloat(),mPaint)
        }
    }



    /**
     * 设置渐变
     */
    private fun setLinearGradient(array: IntArray){
        val size = array.size

        if(size < 2){
            return
        }
        val distance = height / (size + 1f)

        val points = mutableListOf<Float>()
        for(i in 0 until size){
            points.add((distance * i) / height)
        }

        mLinearGradient = LinearGradient(width/2f,0f,width/2f,height*1f,
                array,points.toFloatArray(), Shader.TileMode.CLAMP)

        mPaint.shader = mLinearGradient
        mPaint.isDither = true
        invalidate()
    }

    fun setColorArray(array: IntArray){
        colorArray = array
        invalidate()
    }

    fun isCircleOrRect(boolean: Boolean){
        isCircle = boolean
    }


}