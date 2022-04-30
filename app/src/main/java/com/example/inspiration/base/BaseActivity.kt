package com.example.inspiration.base

/**
 *
 * @ClassName:      com.example.inspiration.base.BaseActivity
 * @Author:         Yan
 * @CreateDate:     12点30分
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:     com.example.inspiration.base.BaseActivity activity的基类
 */

import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.example.inspiration.R
import com.example.inspiration.utils.*
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.toolbar_left_image_back
import kotlinx.android.synthetic.main.toolbar_layout.toolbar_subtitle
import kotlinx.android.synthetic.main.toolbar_layout.toolbar_subtitle_image


abstract class BaseActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName
    val mScreenHeight by lazy{
        val resources: Resources = this.resources
        val dm: DisplayMetrics = resources.displayMetrics
        dm.heightPixels
    }



    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化 View
     */
    abstract fun initView()

    private fun observeUi() {
        LiveEventBus.get<String>(SHOW_TOAST).observe(this) {
            toast(it)
        }

        LiveEventBus.get<Boolean>(LOADING_STATE).observe(this) {
            if (it) showLoading() else hideLoading()
        }
    }


    open fun showLoading() {
        toolbar_title?.visibility = View.GONE
        toolbar_loading?.visibility = View.VISIBLE
        toolbar_loading?.startAnimation(getRotateAnimation(0f, 360f))
    }

    open fun hideLoading() {
        toolbar_title?.visibility = View.VISIBLE
        toolbar_loading?.visibility = View.GONE
        toolbar_loading?.clearAnimation()
    }

    open fun isLoading() = toolbar_loading?.visibility == View.VISIBLE

    override fun onCreate(savedInstanceState: Bundle?) {
        //防止输入法顶起底部布局
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        //去掉系统自带标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        super.onCreate(savedInstanceState)
        //设置状态栏透明
        val window = this.window
        val decorView = window.decorView

        // 这是 Android 做了兼容的 Compat 包
        // 下面这个设置后会沉浸式状态栏和导航栏
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val windowInsetsController = ViewCompat.getWindowInsetsController(decorView)
        // 设置状态栏字体颜色为黑色
        windowInsetsController?.isAppearanceLightStatusBars = true
        //把状态栏颜色设置成透明
        window.statusBarColor = Color.TRANSPARENT

        observeUi()

        initData()
        initView()

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    //设置顶部toolbar相应样式
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    open fun setTop(
        title: String, subTitle: Any? = null, isBack: (() -> Unit)? = {
            toolbar_left_image_back?.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_write_back)
            )
            toolbar_left_image_back?.setOnClickListener { onBackPressed() }
        }
    ) {
        toolbar_title?.text = title
        toolbar_title?.isSelected = true
        //默认显示返回按钮
        isBack?.invoke()
        //根据subtitle的数据类型来显示图片或文字
        when (subTitle) {
            is String -> {
                toolbar_subtitle_image?.visibility = View.GONE
                toolbar_subtitle?.visibility = View.VISIBLE
                toolbar_subtitle?.text = subTitle
            }
            is Int -> {
                toolbar_subtitle?.visibility = View.GONE
                toolbar_subtitle_image?.visibility = View.VISIBLE
                toolbar_subtitle_image?.setImageResource(subTitle)
            }
            else -> {
                toolbar_subtitle?.visibility = View.GONE
                toolbar_subtitle_image?.visibility = View.GONE
            }
        }
    }


    override fun onBackPressed() {
        if(!interceptBackPress()){
            super.onBackPressed()
        }
    }

    private fun interceptBackPress() : Boolean{
        return BackHandlerHelper.handleBackPress(this)
    }
}