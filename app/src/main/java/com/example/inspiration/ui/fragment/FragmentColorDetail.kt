package com.example.inspiration.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inspiration.R
import com.example.inspiration.base.BaseFragment
import com.example.inspiration.databinding.FragmentColorDetailBinding
import com.example.inspiration.databinding.FragmentColorRvBinding
import com.example.inspiration.httpUtils.ColorDetail
import com.example.inspiration.httpUtils.Colors
import com.example.inspiration.httpUtils.Shades
import com.example.inspiration.model.InspirationShaderModel
import com.example.inspiration.ui.adapter.core.ListAdapter
import com.example.inspiration.ui.adapter.core.getModel
import com.example.inspiration.ui.adapter.core.into
import com.example.inspiration.ui.adapter.core.layoutViewModelDsl
import com.example.inspiration.ui.viewModel.ColorViewModel
import com.example.inspiration.ui.widget.CircleShader
import kotlin.properties.Delegates

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.fragment
 * @ClassName:      FragmentColorDetail
 * @Author:         Yan
 * @CreateDate:     2022年05月01日 18:57:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    FragmentColorDetail
 */

class FragmentColorDetail : BaseFragment() {

    private lateinit var fragmentColorDetailBinding: FragmentColorDetailBinding
    private val colorViewModel by activityViewModels<ColorViewModel>()
    private lateinit var idPage: String
    private var position by Delegates.notNull<Int>()
    

    override fun initData() {
        val args = requireArguments()
        idPage = FragmentColorDetailArgs.fromBundle(args).idPager.toString()
        position = FragmentColorDetailArgs.fromBundle(args).position

        colorViewModel.getColorDetail(idPage)
        colorViewModel.colorDetail.observeState(this){
            onSuccess {
                val colorInfo = colorViewModel.colorListSet[idPage.toInt()].value?.data?.color_list?.get(position)
                fragmentColorDetailBinding.colorInfo = colorInfo
                fragmentColorDetailBinding.cvCardColor.setCardBackgroundColor(Color.parseColor("#${colorInfo?.hex}"))
                initShaderCircle(it.shades)
                initColorCard(it.colors)
            }
        }

    }

    override fun initView(view: View) {
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.share_card)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentColorDetailBinding = FragmentColorDetailBinding.inflate(inflater)
        return fragmentColorDetailBinding.root
    }

    override fun handleBackPress(): Boolean {
        findNavController().navigate(R.id.action_nav_color_detail_to_nav_color)
//        Handler(Looper.myLooper()!!).postDelayed(
//            {
//                requireActivity().onBackPressed()
//            },100
//        )
        return true
    }

    private fun initShaderCircle(shades : Shades){
        val listOfShades = mutableListOf<IntArray>()
        //每一层渐变色
        shades.shade_list.forEach { shade ->
            val colors = mutableListOf<Int>()
            //渐变色内每一个颜色
            shade.shade.forEach {
                colors.add(Color.rgb(it.color.r,it.color.g,it.color.b))
            }
            listOfShades.add(colors.toIntArray())
        }
        
        fragmentColorDetailBinding.csColor1.setColorArray(listOfShades[0])
        fragmentColorDetailBinding.csColor2.setColorArray(listOfShades[1])
        fragmentColorDetailBinding.csColor3.setColorArray(listOfShades[2])
        fragmentColorDetailBinding.csColor4.setColorArray(listOfShades[3])
        fragmentColorDetailBinding.csColor5.setColorArray(listOfShades[4])
        fragmentColorDetailBinding.csColor6.setColorArray(listOfShades[5])

    }

    @SuppressLint("SetTextI18n")
    private fun initColorCard(colors : Colors){
        val color1 = colors.color_1
        val color2 = colors.color_2
        val color3 = colors.color_3
        val color4 = colors.color_4
        val color5 = colors.color_5
        val color6 = colors.color_6

        fragmentColorDetailBinding.cvColorCard1.setCardBackgroundColor(Color.parseColor("#${color1.hex}"))
        fragmentColorDetailBinding.cvColorCard2.setCardBackgroundColor(Color.parseColor("#${color2.hex}"))
        fragmentColorDetailBinding.cvColorCard3.setCardBackgroundColor(Color.parseColor("#${color3.hex}"))
        fragmentColorDetailBinding.cvColorCard4.setCardBackgroundColor(Color.parseColor("#${color4.hex}"))
        fragmentColorDetailBinding.cvColorCard5.setCardBackgroundColor(Color.parseColor("#${color5.hex}"))
        fragmentColorDetailBinding.cvColorCard6.setCardBackgroundColor(Color.parseColor("#${color6.hex}"))

        fragmentColorDetailBinding.tvColorCard1.text = "#${color1.hex}"
        fragmentColorDetailBinding.tvColorCard2.text = "#${color2.hex}"
        fragmentColorDetailBinding.tvColorCard3.text = "#${color3.hex}"
        fragmentColorDetailBinding.tvColorCard4.text = "#${color4.hex}"
        fragmentColorDetailBinding.tvColorCard5.text = "#${color5.hex}"
        fragmentColorDetailBinding.tvColorCard6.text = "#${color6.hex}"
    }

}