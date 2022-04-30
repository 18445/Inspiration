package com.example.inspiration.ui.adapter.core

/**
 *
 * @ClassName:      DSL
 * @Author:         Yan
 * @CreateDate:     12点32分
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:     //TODO
 */



inline fun listAdapter(block : ListAdapter.() -> Unit): ListAdapter = ListAdapter().apply{
    block()
}

//Xml的DSL加载
inline fun <M> layoutViewModelDsl(
    layoutRes : Int,
    model : M,
    crossinline init : DefaultViewHolder.() -> Unit
) = LayoutViewModel<M>(layoutRes).apply {
    this.model = model
    onCreateViewHolder {
        init.invoke(this)
    }
}