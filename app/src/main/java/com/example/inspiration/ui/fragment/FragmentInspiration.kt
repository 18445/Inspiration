package com.example.inspiration.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigator
import androidx.navigation.findNavController
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
            onSuccess { list ->
                mListAdapter.add(
                    bindingViewModelDsl(R.layout.item_rv_inspiration,BR.inspirationColor,InspirationRvModel(list[0].name,list[1].name,list[0].image,list[1].image)){
                        //第一个点击
                        itemView.findViewById<ImageView>(R.id.iv_inspiration_pic).setOnClickListener {
                            val bundle = FragmentInspirationVpArgs.Builder()
                                .setIdPager((absoluteAdapterPosition)*2 +1)
                                .build()
                                .toBundle()

                             it.findNavController().navigate(R.id.action_nav_inspiration_to_nav_inspiration_idea,bundle)
                        }
                        //第二个点击
                        itemView.findViewById<ImageView>(R.id.iv_inspiration_pic2).setOnClickListener {
                            val bundle = FragmentInspirationVpArgs.Builder()
                                .setIdPager((absoluteAdapterPosition)*2 +2)
                                .build()
                                .toBundle()

                            it.findNavController().navigate(R.id.action_nav_inspiration_to_nav_inspiration_idea,bundle)
                        }
                    }
                )
                mListAdapter.add(
                    bindingViewModelDsl(R.layout.item_rv_inspiration2,BR.inspirationColor,InspirationRvModel(list[2].name,list[3].name,list[2].image,list[3].image)){
                        //第三个点击
                        itemView.findViewById<ImageView>(R.id.iv_inspiration_pic).setOnClickListener {

                            val bundle = FragmentInspirationVpArgs.Builder()
                                .setIdPager((absoluteAdapterPosition)*2 +1)
                                .build()
                                .toBundle()

                            it.findNavController().navigate(R.id.action_nav_inspiration_to_nav_inspiration_idea,bundle)
                        }
                        //第四个点击
                        itemView.findViewById<ImageView>(R.id.iv_inspiration_pic2).setOnClickListener {
                            val bundle = FragmentInspirationVpArgs.Builder()
                                .setIdPager((absoluteAdapterPosition)*2 +4)
                                .build()
                                .toBundle()

                            it.findNavController().navigate(R.id.action_nav_inspiration_to_nav_inspiration_idea,bundle)
                        }
                    }
                )


            }
        }
    }

}