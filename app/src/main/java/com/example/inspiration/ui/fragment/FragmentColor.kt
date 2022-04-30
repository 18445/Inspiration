package com.example.inspiration.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.inspiration.base.BaseFragment
import com.example.inspiration.databinding.FragmentColorBinding
import com.example.inspiration.ui.viewModel.ColorViewModel

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.fragment
 * @ClassName:      FragmentColor
 * @Author:         Yan
 * @CreateDate:     2022年04月30日 15:41:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    色谱界面
 */

class FragmentColor : BaseFragment() {

    private lateinit var fragmentColorBinding: FragmentColorBinding
    private lateinit var mColorViewModel: ColorViewModel
    private val mColorFragments = mutableListOf<FragmentColorRv>()


    override fun initData() {
        initViewPager()
    }

    override fun initView(view: View) {
        mColorViewModel = ViewModelProvider(this).get(ColorViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentColorBinding = FragmentColorBinding.inflate(inflater)
        return fragmentColorBinding.root
    }

    private fun initViewPager(){
        mColorViewModel.getColorPageId()
        mColorViewModel.colorPage.observeState(this){
            onSuccess {
                it.list.forEach {

                }
            }
        }
    }


}