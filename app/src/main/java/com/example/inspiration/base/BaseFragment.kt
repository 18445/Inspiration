package com.example.inspiration.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 *
 * @ClassName:      com.example.inspiration.base.BaseFragment
 * @Author:         Yan
 * @CreateDate:     12点31分
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:     //TODO
 */


/**
 * 是否处理activity的回退按钮
 * True为拦截
 * false为不做处理
 */
interface OnBackPressListener {

    fun handleBackPress() : Boolean

}

abstract class BaseFragment : Fragment() , OnBackPressListener {

    override fun handleBackPress(): Boolean {
        return false
    }

    val TAG = javaClass.simpleName
    /**
     * 列表接口每页请求的条数
     */
    val pageSize = 20



    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化 View
     */
    abstract fun initView(view: View)

    /**
     * 开始请求
     */
    abstract fun startHttp()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initData()
    }

}