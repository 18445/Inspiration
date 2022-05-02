package com.example.inspiration.ui.adapter.binding

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.inspiration.R

/**
 *
 * @ClassName:      ViewExt
 * @Author:         Yan
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    ViewExt
 */

fun RecyclerView.ViewHolder.getBinding(): ViewDataBinding {
    return itemView.getTag(R.id.list_adapter_binding) as ViewDataBinding
}