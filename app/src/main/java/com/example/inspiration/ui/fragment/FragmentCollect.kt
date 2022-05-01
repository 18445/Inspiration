package com.example.inspiration.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inspiration.base.BaseFragment
import com.example.inspiration.databinding.FragmentCollectBinding


/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.fragment
 * @ClassName:      FragmentCollect
 * @Author:         Yan
 * @CreateDate:     2022年04月30日 15:41:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    收藏界面
 */
class FragmentCollect : BaseFragment() {

    private lateinit var fragmentCollectBinding: FragmentCollectBinding

    override fun initData() {

    }

    override fun initView(view: View) {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentCollectBinding = FragmentCollectBinding.inflate(inflater)
        return fragmentCollectBinding.root
    }

}