package com.example.inspiration.ui.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.inspiration.ui.adapter.core.ListAdapter
import com.example.inspiration.ui.adapter.diff.ArrayListAdapterCallBack
import com.example.inspiration.ui.adapter.diff.ViewModelDiffType

/**
 *
 * @ProjectName:    ToDo
 * @Package:        com.example.todo.ui.adapter.diff
 * @ClassName:      DiffExt
 * @Author:         Yan
 * @CreateDate:     2022年04月20日 21:22:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    DiffExt
 */

fun ListAdapter.calculateDiff(
    newItems: List<ViewModelDiffType>
) {
    val result = DiffUtil.calculateDiff(
        ArrayListAdapterCallBack(
            oldItems = dataList as? List<ViewModelDiffType>
                ?: throw Exception("please let model implements SameModel"),
            newItems = newItems
        )
    )
    replaceAll(newItems)
    result.dispatchUpdatesTo(this)
}
