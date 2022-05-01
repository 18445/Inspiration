package com.example.inspiration.ui.fragment

import android.R
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.util.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import androidx.viewpager2.widget.ViewPager2
import com.example.inspiration.base.BaseFragment
import com.example.inspiration.databinding.FragmentInspirationVpBinding
import com.example.inspiration.ui.adapter.viewpager2.ColorAdapter
import com.example.inspiration.ui.viewModel.InspirationViewModel


/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.fragment
 * @ClassName:      FragmentVp
 * @Author:         Yan
 * @CreateDate:     2022年05月01日 11:48:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    FragmentInspirationVp
 */
class FragmentInspirationVp: BaseFragment() {

    private lateinit var idPage: String
    private lateinit var fragmentInspirationVpBinding: FragmentInspirationVpBinding
    private val mInspirationViewModel by activityViewModels<InspirationViewModel>()
    private val mInspirationFragments = mutableListOf<Fragment>()
    private lateinit var mThemes : SparseArray<String>

    override fun initData() {
        val args = requireArguments()
        idPage = FragmentInspirationVpArgs.fromBundle(args).idPager.toString()
        initViewPager()
    }

    override fun initView(view: View) {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentInspirationVpBinding = FragmentInspirationVpBinding.inflate(inflater)
        return fragmentInspirationVpBinding.root
    }

    private fun initViewPager(){
        mInspirationViewModel.getInspirationList(idPage)
        mInspirationViewModel.mInspirationList.observeState(this){
            onSuccess { inspirationPage ->
                mInspirationViewModel.mThemes.observe(requireActivity()){
                    mThemes = it
                }
                inspirationPage.forEach {
                    mInspirationFragments.add(FragmentInspirationRv(it.ideaDetail.toString()) as Fragment)
                    fragmentInspirationVpBinding.vp2Inspiration.adapter =
                        ColorAdapter(requireActivity().supportFragmentManager,mInspirationFragments,
                            LifecycleRegistry(requireActivity()).apply {
                            currentState = Lifecycle.State.RESUMED
                        }
                        )
                    fragmentInspirationVpBinding.vp2Inspiration.offscreenPageLimit = inspirationPage.size
                    fragmentInspirationVpBinding.vp2Inspiration.setCurrentItem(0,false)
                }
            }
        }
        fragmentInspirationVpBinding.vp2Inspiration.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if(this@FragmentInspirationVp::mThemes.isLateinit){
                    if(mThemes.isNotEmpty()){
                        fragmentInspirationVpBinding.title = mThemes[position+1]
                    }else{
                        fragmentInspirationVpBinding.title = "立春"
                    }
                }else{
                    fragmentInspirationVpBinding.title = "立春"
                }
                fragmentInspirationVpBinding.ciInspiration.selectPage(position)
            }
        })
    }

}