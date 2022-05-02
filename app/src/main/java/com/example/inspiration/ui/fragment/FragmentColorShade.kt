package com.example.inspiration.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.inspiration.R
import com.example.inspiration.base.BaseFragment
import com.example.inspiration.databinding.FragmentColorShadeBinding
import com.example.inspiration.httpUtils.Shades
import com.example.inspiration.ui.viewModel.ColorViewModel
import kotlin.properties.Delegates

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.fragment
 * @ClassName:      FragmentColorShade
 * @Author:         Yan
 * @CreateDate:     2022年05月02日 00:19:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    FragmentColorShade
 */
class FragmentColorShade : BaseFragment() {

    private lateinit var fragmentColorShadeBinding: FragmentColorShadeBinding
    private val colorViewModel by activityViewModels<ColorViewModel>()
    private var position by Delegates.notNull<Int>()

    override fun initData() {
        val args = requireArguments()
        position = FragmentColorDetailArgs.fromBundle(args).position
        initShaderCircle(colorViewModel.colorDetail.value!!.data!!.shades)
    }

    override fun initView(view: View) {
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.share_card)
        fragmentColorShadeBinding.csColorShadeBg.isCircleOrRect(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentColorShadeBinding = FragmentColorShadeBinding.inflate(inflater)
        return fragmentColorShadeBinding.root
    }

    private fun initShaderCircle(shades : Shades) {
        val listOfShades = mutableListOf<IntArray>()
        //每一层渐变色
        shades.shade_list.forEach { shade ->
            val colors = mutableListOf<Int>()
            //渐变色内每一个颜色
            shade.shade.forEach {
                colors.add(Color.rgb(it.color.r, it.color.g, it.color.b))
            }
            listOfShades.add(colors.toIntArray())
        }
        fragmentColorShadeBinding.csColorShadeBg.setColorArray(listOfShades[position - 1])
    }


}