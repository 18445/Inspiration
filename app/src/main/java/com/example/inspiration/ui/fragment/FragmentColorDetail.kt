package com.example.inspiration.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.inspiration.R
import com.example.inspiration.base.BaseFragment
import com.example.inspiration.databinding.FragmentColorDetailBinding
import com.example.inspiration.httpUtils.Colors
import com.example.inspiration.httpUtils.Shades
import com.example.inspiration.ui.viewModel.ColorViewModel
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

        fragmentColorDetailBinding.apply {
            csColor1.apply {
                setColorArray(listOfShades[0])
                setOnClickListener {
                it.transitionName = "cs_color_shade"
                val backgroundPair = Pair(it,"cs_color_shade")
                val extras = FragmentNavigatorExtras(backgroundPair)

                val bundle = FragmentColorShadeArgs.Builder()
                    .setPosition(1)
                    .build()
                    .toBundle()

                findNavController().navigate(R.id.action_nav_color_detail_to_nav_color_shade,
                    bundle,
                    null,
                    extras)
            }
            }

            csColor2.apply {
                setColorArray(listOfShades[1])
                setOnClickListener {
                it.transitionName = "cs_color_shade"
                val backgroundPair = Pair(it,"cs_color_shade")
                val extras = FragmentNavigatorExtras(backgroundPair)

                val bundle = FragmentColorShadeArgs.Builder()
                    .setPosition(2)
                    .build()
                    .toBundle()

                findNavController().navigate(R.id.action_nav_color_detail_to_nav_color_shade,
                    bundle,
                    null,
                    extras)
            }
            }

            csColor3.apply {
                setColorArray(listOfShades[2])
                setOnClickListener {
                it.transitionName = "cs_color_shade"
                val backgroundPair = Pair(it,"cs_color_shade")
                val extras = FragmentNavigatorExtras(backgroundPair)

                val bundle = FragmentColorShadeArgs.Builder()
                    .setPosition(3)
                    .build()
                    .toBundle()

                findNavController().navigate(R.id.action_nav_color_detail_to_nav_color_shade,
                    bundle,
                    null,
                    extras)
            }
            }

            csColor4.apply {
                setColorArray(listOfShades[3])
                setOnClickListener {
                it.transitionName = "cs_color_shade"
                val backgroundPair = Pair(it,"cs_color_shade")
                val extras = FragmentNavigatorExtras(backgroundPair)

                val bundle = FragmentColorShadeArgs.Builder()
                    .setPosition(4)
                    .build()
                    .toBundle()

                findNavController().navigate(R.id.action_nav_color_detail_to_nav_color_shade,
                    bundle,
                    null,
                    extras)
            }
            }

            csColor5.apply {
                setColorArray(listOfShades[4])
                setOnClickListener {
                it.transitionName = "cs_color_shade"
                val backgroundPair = Pair(it,"cs_color_shade")
                val extras = FragmentNavigatorExtras(backgroundPair)

                val bundle = FragmentColorShadeArgs.Builder()
                    .setPosition(5)
                    .build()
                    .toBundle()

                findNavController().navigate(R.id.action_nav_color_detail_to_nav_color_shade,
                    bundle,
                    null,
                    extras)
            }
            }

            csColor6.apply {
                setColorArray(listOfShades[5])
                setOnClickListener {
                it.transitionName = "cs_color_shade"
                val backgroundPair = Pair(it,"cs_color_shade")
                val extras = FragmentNavigatorExtras(backgroundPair)

                val bundle = FragmentColorShadeArgs.Builder()
                    .setPosition(6)
                    .build()
                    .toBundle()

                findNavController().navigate(R.id.action_nav_color_detail_to_nav_color_shade,
                    bundle,
                    null,
                    extras)
            }
            }

        }

    }

    @SuppressLint("SetTextI18n")
    private fun initColorCard(colors : Colors){
        val color1 = colors.color_1
        val color2 = colors.color_2
        val color3 = colors.color_3
        val color4 = colors.color_4
        val color5 = colors.color_5
        val color6 = colors.color_6

        fragmentColorDetailBinding.apply {

            cvColorCard1.apply { setCardBackgroundColor(Color.parseColor("#${color1.hex}")) }
            cvColorCard2.apply { setCardBackgroundColor(Color.parseColor("#${color2.hex}")) }
            cvColorCard3.apply { setCardBackgroundColor(Color.parseColor("#${color3.hex}")) }
            cvColorCard4.apply { setCardBackgroundColor(Color.parseColor("#${color4.hex}")) }
            cvColorCard5.apply { setCardBackgroundColor(Color.parseColor("#${color5.hex}")) }
            cvColorCard6.apply { setCardBackgroundColor(Color.parseColor("#${color6.hex}")) }

            tvColorCard1.apply { text = "#${color1.hex}" }
            tvColorCard2.apply { text = "#${color2.hex}" }
            tvColorCard3.apply { text = "#${color3.hex}" }
            tvColorCard4.apply { text = "#${color4.hex}" }
            tvColorCard5.apply { text = "#${color5.hex}" }
            tvColorCard6.apply { text = "#${color6.hex}" }
        }

    }

}