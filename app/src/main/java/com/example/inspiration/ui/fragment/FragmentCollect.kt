package com.example.inspiration.ui.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.allViews
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.inspiration.R
import com.example.inspiration.base.BaseFragment
import com.example.inspiration.constant.Constant
import com.example.inspiration.databinding.FragmentCollectBinding
import com.example.inspiration.httpUtils.Shades
import com.example.inspiration.httpUtils.StarList
import com.example.inspiration.model.CollectModel
import com.example.inspiration.model.InspirationShaderModel
import com.example.inspiration.ui.adapter.core.ListAdapter
import com.example.inspiration.ui.adapter.core.getModel
import com.example.inspiration.ui.adapter.core.into
import com.example.inspiration.ui.adapter.core.layoutViewModelDsl
import com.example.inspiration.ui.viewModel.CollectViewModel
import com.example.inspiration.ui.widget.CircleShader
import com.example.inspiration.utils.StarCardItemTouchHelper
import com.example.inspiration.utils.screenshot
import com.example.inspiration.utils.shareImg
import com.example.inspiration.utils.toast
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.item_rv_collect_card.*


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
    private lateinit var collectViewModel: CollectViewModel

    private val mListAdapter by lazy {
        ListAdapter()
    }

    override fun initData() {
        Log.d(Constant.Access_USER_TOKEN,MMKV.defaultMMKV().decodeString(Constant.Access_USER_TOKEN)!!)
        Log.d(Constant.Refresh_USER_TOKEN,MMKV.defaultMMKV().decodeString(Constant.Refresh_USER_TOKEN)!!)
        getStarList()
        getPermission()
    }

    /**
     * 请求获得用户权限
     */
    private fun getPermission() {

        val permissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                when {
                    permissions.getOrDefault(Manifest.permission.WRITE_EXTERNAL_STORAGE, false) -> {
                        Log.d("permission", "Manifest.permission.WRITE_EXTERNAL_STORAGE")
                    }
                    permissions.getOrDefault(Manifest.permission.READ_EXTERNAL_STORAGE, false) -> {
                        Log.d("permission", "Manifest.permission.READ_EXTERNAL_STORAGE")
                    }
                    else -> {
                        toast("没有权限")
                    }
                }
            }
        }

        //获取用户权限
        permissionRequest.launch(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        )
    }

    override fun initView(view: View) {
        collectViewModel = ViewModelProvider(this).get(CollectViewModel::class.java)
        fragmentCollectBinding.title = "收藏"
        mListAdapter.into(fragmentCollectBinding.rvCollectCard)
        ItemTouchHelper(StarCardItemTouchHelper(mListAdapter)).attachToRecyclerView(fragmentCollectBinding.rvCollectCard)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentCollectBinding = FragmentCollectBinding.inflate(inflater)
        return fragmentCollectBinding.root
    }

    private fun getStarList(){

        var index = 1
        collectViewModel.getStarList(index)
        collectViewModel.starList.observeState(this){
            onSuccess {
                if(!it.has_more && index < 20){
                    collectViewModel.getStarList(++index)
                }
                setShaderRv(it)
            }
        }



    }

    private fun setShaderRv(starList: StarList ){
        //每一层渐变色
        starList.star_list.forEach { star ->
            val colors = mutableListOf<Int>()
            //渐变色内每一个颜色
            star.colorShade.forEach {
                colors.add(Color.rgb(it.color.r,it.color.g,it.color.b))
            }

            mListAdapter.add(
                layoutViewModelDsl(
                    R.layout.item_rv_collect_card,
                    CollectModel(InspirationShaderModel(colors.toIntArray()),star.name)
                ){

                    onBindViewHolder {
                        val model = getModel<CollectModel>()

                        if(model != null){

                            itemView.findViewById<ImageView>(R.id.iv_collect_share).setOnClickListener {
                                this@FragmentCollect.requireActivity().shareImg(itemView.findViewById<CircleShader>(R.id.cs_collect_shade))
                            }

                            itemView.findViewById<TextView>(R.id.tv_collect_title).text = model.name

                            //右侧渐变区
                            itemView.findViewById<CircleShader>(R.id.cs_collect_shade).apply {
                                setColorArray(model.inspirationShaderModel.colorArray)
                                isCircleOrRect(false)
                            }

                            itemView.findViewById<LinearLayout>(R.id.ll_collect_color).apply {

                                if(childCount > 1){
                                    this.removeAllViewsInLayout()
                                }

                                val params = LinearLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT)
                                params.weight = 1f

                                model.inspirationShaderModel.colorArray.forEach {
                                    val imageView = ImageView(requireContext())
                                    imageView.setBackgroundColor(it)
                                    imageView.layoutParams = params
                                    addView(imageView)
                                }
                            }
                        }
                    }

                }
            )
        }
    }
}