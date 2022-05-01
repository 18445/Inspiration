package com.example.inspiration.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.inspiration.BR
import com.example.inspiration.R
import com.example.inspiration.base.BaseFragment
import com.example.inspiration.databinding.FragmentColorRvBinding
import com.example.inspiration.httpUtils.Color
import com.example.inspiration.ui.adapter.animators.firstAnimation
import com.example.inspiration.ui.adapter.animators.updateAnimation
import com.example.inspiration.ui.adapter.binding.BindingViewModel
import com.example.inspiration.ui.adapter.binding.bindingViewModelDsl
import com.example.inspiration.ui.adapter.core.ListAdapter
import com.example.inspiration.ui.adapter.core.getView
import com.example.inspiration.ui.adapter.core.getViewModel
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


class FragmentColorRv (private val idPaper:String): BaseFragment() {

    private lateinit var fragmentColorRvBinding: FragmentColorRvBinding
    private val mColorViewModel by activityViewModels<ColorViewModel>()

    private val mListAdapter by lazy{
        ListAdapter()
    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
        mListAdapter.into(fragmentColorRvBinding.rvColor)
        mColorViewModel.getColorList(idPaper)
        mColorViewModel.colorListSet[idPaper.toInt()].observeState(this){
            onSuccess { colorList ->
                colorList.color_list.forEach { color ->
                    mListAdapter.add(
                        bindingViewModelDsl(R.layout.item_rv_color,BR.colorInfo,color){

                            onBindViewHolder {
                                val viewModel = getViewModel<BindingViewModel<Color>>()
                                if(viewModel?.model != null){
                                    val colorR = viewModel.model?.r!!
                                    val colorG = viewModel.model?.g!!
                                    val colorB = viewModel.model?.b!!

                                    val cardView = itemView.findViewById<CardView>(R.id.cv_color)
                                    cardView.setCardBackgroundColor(android.graphics.Color.rgb(colorR,colorG,colorB))

                                }

                                itemView.findViewById<CardView>(R.id.cv_color).setOnClickListener {
                                    //元素动画
                                    it.transitionName = "cv_avatar_color_card"
                                    val imagePair = Pair(it,"cv_avatar_color_card")
                                    val extras = FragmentNavigatorExtras(imagePair)

                                    val bundle = FragmentColorDetailArgs.Builder()
                                        .setIdPager(idPaper.toInt())
                                        .setPosition(absoluteAdapterPosition)
                                        .build()
                                        .toBundle()

                                    findNavController().navigate(R.id.action_nav_color_to_nav_color_detail,
                                        bundle,
                                        null,
                                        extras)
                                }
                            }

                            onViewAttachedToWindow {
                                firstAnimation()
                                updateAnimation()
                            }

                        }

                    )
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