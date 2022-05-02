package com.example.inspiration.ui.adapter.binding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.inspiration.R
import com.example.inspiration.ui.adapter.core.DefaultViewHolder
import com.example.inspiration.ui.adapter.core.DefaultViewModel

/**
 *
 * @ClassName:      BindingViewModel
 * @Author:         Yan
 * @CreateDate:     12点40分
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    BindingViewModel 用于DataBinding的绑定操作
 */

open class BindingViewModel<M>(override val layoutRes: Int, private val variableId: Int) :
    DefaultViewModel<M>() {

    override fun getHolderItemView(parent: ViewGroup, layoutInflater: LayoutInflater): View {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            layoutRes,
            parent,
            false
        )
        val view = binding.root
        view.setTag(R.id.list_adapter_binding, binding)
        return view
    }

    override fun bindVH(viewHolder: DefaultViewHolder, payloads: List<Any>) {
        viewHolder.getBinding().setVariable(variableId, model)
        viewHolder.getBinding().executePendingBindings()
    }

    override fun unBindVH(viewHolder: DefaultViewHolder) {
        viewHolder.getBinding().unbind()
    }

}