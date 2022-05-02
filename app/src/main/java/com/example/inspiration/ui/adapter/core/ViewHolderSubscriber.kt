package com.example.inspiration.ui.adapter.core

import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @ClassName:      ViewHolderSubcriber
 * @Author:         Yan
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
interface Subscriber {
    fun onBindViewHolder(position : Int,payloads : List<Any>){}
    fun unBindViewHolder(position : Int)
    fun onViewAttachedToWindow(holder : RecyclerView.ViewHolder,position: Int){}
    fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder,position: Int){}
}