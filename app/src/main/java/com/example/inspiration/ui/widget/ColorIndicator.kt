package com.example.inspiration.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.absoluteValue

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.widget
 * @ClassName:      ColorIndicator
 * @Author:         Yan
 * @CreateDate:     2022年04月30日 20:01:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    ColorIndicator
 */
class ColorIndicator constructor(
    context: Context,
    attrs: AttributeSet?,
) : View(context, attrs) {

    //总的数量
    private val mTotal = 7
    private val distance by lazy {
        mHalfWidth / mTotal *  1.2f
    }
    private val mHalfWidth by lazy {
        width / 2
    }

    private val size by lazy {
        (largeSize - smallSize) / mTotal
    }

    private val largeSize = 15f
    private val smallSize = 5f


    private val mNormalPaint = Paint()
    private val mSelectedPaint = Paint()

    private var selectedIndex = 0

    init {
        mNormalPaint.apply {
            isAntiAlias = true
            color = Color.WHITE
            style = Paint.Style.FILL
            textSize = 5f
        }
        mSelectedPaint.apply {
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.FILL
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for(i in 0 until mTotal){
            roundDrawCircle(canvas,i, i == (mTotal - 1 - selectedIndex))
        }
    }


    private fun roundDrawCircle(canvas: Canvas,index : Int, isSelected : Boolean){
        canvas.drawCircle(mHalfWidth + ((mTotal/2f) - index ) * distance - distance/2, height/2f ,largeSize - ((mTotal/2) - index).absoluteValue*size,
            if(isSelected) mSelectedPaint else mNormalPaint)
    }

    fun selectPage(index :Int){
        selectedIndex = index
        invalidate()
    }

}