package com.example.inspiration.ui.adapter.expandable

import android.util.SparseArray
import android.util.SparseBooleanArray
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @ProjectName:    ToDo
 * @Package:        com.example.todo.ui.adapter.expandable
 * @ClassName:      ExpandableExt
 * @Author:         Yan
 * @CreateDate:     2022年04月29日 21:01:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    可扩展的RecyclerView
 */

private val expandedItemsCache = SparseArray<SparseBooleanArray>()
private val expandConfigCache = SparseArray<SparseArray<Any>>()

private val defaultExpandConfig by lazy {
    SparseArray<Any>().apply {
        append(0, true) // is Multi Expandable
    }
}

private fun getExpandedItems(key: Int): SparseBooleanArray {
    return expandedItemsCache[key] ?: SparseBooleanArray().apply {
        expandedItemsCache.append(key, this)
    }
}

private fun getExpandConfig(key: Int): SparseArray<Any> {
    return expandConfigCache[key] ?: defaultExpandConfig.apply {
        expandConfigCache.append(key, this)
    }
}

val RecyclerView.Adapter<*>.isMultiExpand: Boolean
    get() = getExpandConfig(hashCode())[0] as Boolean

val RecyclerView.Adapter<*>.expandedCount: Int
    get() = getExpandedItems(hashCode()).size()

/**
 * 设置是否支持多开列表
 * @param enable true -> 多开   false -> 单开
 */
fun RecyclerView.Adapter<*>.setMultiExpandable(enable: Boolean) {
    getExpandConfig(hashCode()).setValueAt(0, enable)
    if (!enable && expandedCount >= 1) {
        clearExpanded()
    }
}

fun RecyclerView.Adapter<*>.getExpandedItems(): List<Int> {
    val eis = getExpandedItems(hashCode())
    val size = eis.size()
    val items: MutableList<Int> = ArrayList(size)
    for (i in 0 until size) {
        items.add(eis.keyAt(i))
    }
    return items
}

/**
 * 使所有展开的列表收缩
 */
fun RecyclerView.Adapter<*>.clearExpanded() {
    val selection = getExpandedItems()
    getExpandedItems(hashCode()).clear()
    for (i in selection) {
        notifyItemChanged(i)
    }
}

/**
 * 判断列表是否是展开状态
 */
fun RecyclerView.Adapter<*>.isExpanded(position: Int) = getExpandedItems().contains(position)

/**
 * 展开item
 */
fun RecyclerView.Adapter<*>.toggleExpand(position: Int) {
    if (!isMultiExpand) {
        clearExpanded()
    }
    val eis = getExpandedItems(hashCode())
    if (eis.get(position, false)) {
        eis.delete(position)
    } else {
        eis.put(position, true)
    }
    notifyItemChanged(position)
}

fun RecyclerView.Adapter<*>.onDestroy() {
    val key = hashCode()
    expandedItemsCache[key]?.clear()
    expandConfigCache[key]?.clear()
}