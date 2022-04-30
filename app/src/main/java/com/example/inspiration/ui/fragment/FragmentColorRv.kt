package com.example.inspiration.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.inspiration.BR
import com.example.inspiration.R
import com.example.inspiration.base.BaseFragment
import com.example.inspiration.databinding.FragmentColorRvBinding
import com.example.inspiration.ui.adapter.binding.bindingViewModelDsl
import com.example.inspiration.ui.adapter.core.ListAdapter
import com.example.inspiration.ui.adapter.core.into
import com.example.inspiration.ui.viewModel.ColorViewModel


/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.fragment
 * @ClassName:      ColorItem
 * @Author:         Yan
 * @CreateDate:     2022年04月30日 16:48:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    色谱界面的每一个fragment
 */


class FragmentColorRv (id:Int): BaseFragment() {

    private lateinit var fragmentColorRvBinding: FragmentColorRvBinding
    private val mColorViewModel by activityViewModels<ColorViewModel>()

    private val mListAdapter by lazy{
        ListAdapter()
    }

    override fun initData() {
        mListAdapter.into(fragmentColorRvBinding.rvColor)
        mColorViewModel.getColorList(id)
        mColorViewModel.colorList.observeState(this){
            onSuccess { colorList ->
                colorList.color_list.forEach {
//                    mListAdapter.add(
//                        bindingViewModelDsl(R.layout.fragment_color_rv,BR.model,it){
//
//                        }
//                    )

                }
            }
        }
    }

    override fun initView(view: View) {

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentColorRvBinding = FragmentColorRvBinding.inflate(inflater)
        return fragmentColorRvBinding.root
    }

}