package com.example.inspiration.ui.adapter.core

import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @ProjectName:    ToDo
 * @Package:        com.example.todo.ui.adapter.core
 * @ClassName:      ViewHolderSubcriber
 * @Author:         Yan
 * @CreateDate:     2022年04月20日 00:18:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:     //TODO
 */
interface Subscriber {
    fun onBindViewHolder(position : Int,payloads : List<Any>){}
    fun unBindViewHolder(position : Int)
    fun onViewAttachedToWindow(holder : RecyclerView.ViewHolder,position: Int){}
    fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder,position: Int){}
}