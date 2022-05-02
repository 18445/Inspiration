package com.example.inspiration.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inspiration.R
import com.example.inspiration.base.BaseFragment
import com.example.inspiration.databinding.FragmentInspirationRvBinding
import com.example.inspiration.httpUtils.Colors
import com.example.inspiration.httpUtils.Shades
import com.example.inspiration.model.InspirationCardModel
import com.example.inspiration.model.InspirationDetailModel
import com.example.inspiration.model.InspirationShaderModel
import com.example.inspiration.ui.adapter.core.ListAdapter
import com.example.inspiration.ui.adapter.core.getModel
import com.example.inspiration.ui.adapter.core.into
import com.example.inspiration.ui.adapter.core.layoutViewModelDsl
import com.example.inspiration.ui.viewModel.InspirationViewModel
import com.example.inspiration.ui.widget.CircleShader

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.fragment
 * @ClassName:      FragmentInspirationRv
 * @Author:         Yan
 * @CreateDate:     2022年05月01日 12:06:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    FragmentInspirationRv
 */

class FragmentInspirationRv (private val idPaper:String): BaseFragment() {



    private lateinit var fragmentInspirationRvBinding: FragmentInspirationRvBinding
    private val mInspirationViewModel by activityViewModels<InspirationViewModel>()

    private val mListShaderAdapter by lazy{
        ListAdapter()
    }
    private val mListCardAdapter by lazy {
        ListAdapter()
    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
        mListShaderAdapter.into(fragmentInspirationRvBinding.rvInspirationShader)
        mInspirationViewModel.getInspirationDetail(idPaper)
        mInspirationViewModel.mInspirationDetailSet[idPaper.toInt()].observeState(this){
            onSuccess { inspirationDetail ->
                mInspirationViewModel.mThemes.value?.put(idPaper.toInt(), inspirationDetail.title)
                fragmentInspirationRvBinding.detail = InspirationDetailModel(inspirationDetail.image)
                setShaderRv(inspirationDetail.shades)
                setCardRv(inspirationDetail.colors)
            }
        }
    }

    override fun initView(view: View) {
        mListShaderAdapter.into(fragmentInspirationRvBinding.rvInspirationShader)

        val gridLayoutManager = GridLayoutManager(requireContext(),2)
        gridLayoutManager.orientation = GridLayoutManager.HORIZONTAL
        mListCardAdapter.into(fragmentInspirationRvBinding.rvInspirationCard,gridLayoutManager)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentInspirationRvBinding = FragmentInspirationRvBinding.inflate(inflater)
        return fragmentInspirationRvBinding.root
    }

    private fun setShaderRv(shades : Shades){
        //每一层渐变色
        shades.shade_list.forEach { shade ->
            val colors = mutableListOf<Int>()
            //渐变色内每一个颜色
            shade.shade.forEach {
                Log.d("shadeColors","${it.color.r},${it.color.g},${it.color.b}")
                colors.add(Color.rgb(it.color.r,it.color.g,it.color.b))
            }

            mListShaderAdapter.add(
                layoutViewModelDsl(R.layout.item_rv_inspiration_shader,InspirationShaderModel(colors.toIntArray())){
                    onBindViewHolder {
                        val model = getModel<InspirationShaderModel>()
                        Log.d("InspirationShaderModel",model.toString())
                        if(model != null){
                            itemView.findViewById<CircleShader>(R.id.cs_inspiration).setColorArray(model.colorArray)
                        }
                    }
                }
            )
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setCardRv(color : Colors){

        val color1 = InspirationCardModel(color.color_1.hex,color.color_1.r,color.color_1.g,color.color_1.b)
        val color2 = InspirationCardModel(color.color_2.hex,color.color_2.r,color.color_2.g,color.color_2.b)
        val color3 = InspirationCardModel(color.color_3.hex,color.color_3.r,color.color_3.g,color.color_3.b)
        val color4 = InspirationCardModel(color.color_4.hex,color.color_4.r,color.color_4.g,color.color_4.b)
        val color5 = InspirationCardModel(color.color_5.hex,color.color_5.r,color.color_5.g,color.color_5.b)
        val color6 = InspirationCardModel(color.color_6.hex,color.color_6.r,color.color_6.g,color.color_6.b)
        val color7 = InspirationCardModel(color.color_7.hex,color.color_7.r,color.color_7.g,color.color_7.b)

        mListCardAdapter.add(
            layoutViewModelDsl(R.layout.item_rv_inspiration_colorcard,color1){
                onBindViewHolder {
                    val model = getModel<InspirationCardModel>()
                    if(model != null){
                        itemView.findViewById<CardView>(R.id.cv_inspiration_card).setCardBackgroundColor(Color.rgb(model.r,model.g,model.b))
                        itemView.findViewById<TextView>(R.id.tv_inspiration_card).text = "#${model.background}"
                    }
                }
            }
        )
        mListCardAdapter.add(
            layoutViewModelDsl(R.layout.item_rv_inspiration_colorcard,color2){
                onBindViewHolder {
                    val model = getModel<InspirationCardModel>()
                    if(model != null){
                        itemView.findViewById<CardView>(R.id.cv_inspiration_card).setCardBackgroundColor(Color.rgb(model.r,model.g,model.b))
                        itemView.findViewById<TextView>(R.id.tv_inspiration_card).text = "#${model.background}"
                    }
                }
            }
        )
        mListCardAdapter.add(
            layoutViewModelDsl(R.layout.item_rv_inspiration_colorcard,color3){
                onBindViewHolder {
                    val model = getModel<InspirationCardModel>()
                    if(model != null){
                        itemView.findViewById<CardView>(R.id.cv_inspiration_card).setCardBackgroundColor(Color.rgb(model.r,model.g,model.b))
                        itemView.findViewById<TextView>(R.id.tv_inspiration_card).text = "#${model.background}"
                    }
                }
            }
        )
        mListCardAdapter.add(
            layoutViewModelDsl(R.layout.item_rv_inspiration_colorcard,color4){
                onBindViewHolder {
                    val model = getModel<InspirationCardModel>()
                    if(model != null){
                        itemView.findViewById<CardView>(R.id.cv_inspiration_card).setCardBackgroundColor(Color.rgb(model.r,model.g,model.b))
                        itemView.findViewById<TextView>(R.id.tv_inspiration_card).text = "#${model.background}"
                    }
                }
            }
        )
        mListCardAdapter.add(
            layoutViewModelDsl(R.layout.item_rv_inspiration_colorcard,color5){
                onBindViewHolder {
                    val model = getModel<InspirationCardModel>()
                    if(model != null){
                        itemView.findViewById<CardView>(R.id.cv_inspiration_card).setCardBackgroundColor(Color.rgb(model.r,model.g,model.b))
                        itemView.findViewById<TextView>(R.id.tv_inspiration_card).text = "#${model.background}"
                    }
                }
            }
        )
        mListCardAdapter.add(
            layoutViewModelDsl(R.layout.item_rv_inspiration_colorcard,color6){
                onBindViewHolder {
                    val model = getModel<InspirationCardModel>()
                    if(model != null){
                        itemView.findViewById<CardView>(R.id.cv_inspiration_card).setCardBackgroundColor(Color.rgb(model.r,model.g,model.b))
                        itemView.findViewById<TextView>(R.id.tv_inspiration_card).text = "#${model.background}"
                    }
                }
            }
        )
        mListCardAdapter.add(
            layoutViewModelDsl(R.layout.item_rv_inspiration_colorcard,color7){
                onBindViewHolder {
                    val model = getModel<InspirationCardModel>()
                    if(model != null){
                        itemView.findViewById<CardView>(R.id.cv_inspiration_card).setCardBackgroundColor(Color.rgb(model.r,model.g,model.b))
                        itemView.findViewById<TextView>(R.id.tv_inspiration_card).text = "#${model.background}"
                    }
                }
            }
        )
    }

}