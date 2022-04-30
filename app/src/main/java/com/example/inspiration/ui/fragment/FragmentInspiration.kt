package com.example.inspiration.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.inspiration.BR
import com.example.inspiration.R
import com.example.inspiration.base.BaseFragment
import com.example.inspiration.databinding.FragmentInspirationBinding
import com.example.inspiration.model.InspirationRvModel
import com.example.inspiration.ui.adapter.binding.bindingViewModelDsl
import com.example.inspiration.ui.adapter.core.ListAdapter
import com.example.inspiration.ui.adapter.core.into
import com.example.inspiration.ui.viewModel.InspirationViewModel

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.fragment
 * @ClassName:      FragmentInspiration
 * @Author:         Yan
 * @CreateDate:     2022年04月30日 15:41:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    灵感界面
 */
class FragmentInspiration : BaseFragment() {
    private lateinit var fragmentInspirationBinding: FragmentInspirationBinding
    private lateinit var inspirationViewModel: InspirationViewModel
    private val mListAdapter by lazy {
        ListAdapter()
    }

    override fun initData() {
        mListAdapter.into(fragmentInspirationBinding.rvInspiration)
        getInspirationHome()

            Glide.with(requireContext())
//                .load("https://p3.music.126.net/r0SWUq-8SUHJVxV4yDXE6g==/109951163097373562.jpg")
                .load("https://md.udday.cn/danqing/temp/bl.png")
                .into(fragmentInspirationBinding.ivTest)

    }

    override fun initView(view: View) {
        inspirationViewModel = ViewModelProvider(this).get(InspirationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentInspirationBinding = FragmentInspirationBinding.inflate(inflater)
        return fragmentInspirationBinding.root
    }

    private fun getInspirationHome(){
        inspirationViewModel.getInspirationHome()
        inspirationViewModel.mInspirationHome.observeState(this){
            onSuccess {
                mListAdapter.add(
                    bindingViewModelDsl(R.layout.item_rv_inspiration,BR.inspirationColor,InspirationRvModel(it[0].name,it[1].name,it[0].image,it[1].image)){


                        onBindViewHolder {
                        }
                    }
                )
                mListAdapter.add(
                    bindingViewModelDsl(R.layout.item_rv_inspiration2,BR.inspirationColor,InspirationRvModel(it[2].name,it[3].name,it[2].image,it[3].image)){
                        onBindViewHolder {

                        }
                    }
                )


            }
        }
    }

}