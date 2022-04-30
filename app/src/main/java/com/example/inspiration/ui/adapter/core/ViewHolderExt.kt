package com.example.inspiration.ui.adapter.core

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import com.example.inspiration.R

/**
 *
 * @ProjectName:    ToDo
 * @Package:        com.example.todo.ui.adapter.core
 * @ClassName:      ViewHolderExt
 * @Author:         Yan
 * @CreateDate:     2022年04月20日 00:23:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    ViewHolderExt
 */

inline fun <reified Adapter> RecyclerView.ViewHolder.getAdapter() : Adapter ?{
    return itemView.getTag(R.id.adapter) as? Adapter
}

fun RecyclerView.ViewHolder.getRecyclerView() : RecyclerView?{
    return itemView.getTag(R.id.adapter_recyclerView) as? RecyclerView
}

inline fun <reified  VM : ViewModelType> RecyclerView.ViewHolder.getViewModel()  : VM?{
    return itemView.getTag(R.id.adapter_item) as? VM
}

inline fun <reified M> RecyclerView.ViewHolder.getModel() : M?{
//    return  (itemView.getTag(R.id.adapter_item) as? ViewModelType)?.model
    return (itemView.getTag(R.id.adapter_item) as? ViewModel<M, *>)?.model
}

inline fun<reified T : View> RecyclerView.ViewHolder.getView(@IdRes viewId : Int) : T{
    return itemView.findViewById(viewId) as T
}

//两个参数(DefaultViewHolder,payloads)
internal typealias BindView = DefaultViewHolder.(payloads: List<Any>) -> Unit

//一个参数(DefaultViewHolder)
internal typealias ViewHolderType = DefaultViewHolder.() -> Unit


open class DefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), Subscriber {

    private var bindView: BindView? = null
    private var unBindView: ViewHolderType? = null
    private var onViewAttached: ViewHolderType? = null
    private var onViewDetached: ViewHolderType? = null

    fun onBindViewHolder(f: BindView) {
        bindView = f
    }

    fun onUnBindViewHolder(f: ViewHolderType) {
        unBindView = f
    }

    fun onViewAttachedToWindow(f: ViewHolderType) {
        onViewAttached = f
    }

    fun onViewDetachedFromWindow(f: ViewHolderType) {
        onViewDetached = f
    }

    override fun onBindViewHolder(position: Int, payloads: List<Any>) {
        bindView?.invoke(this, payloads)
    }

    override fun unBindViewHolder(position: Int) {
        unBindView?.invoke(this)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder, position: Int) {
        onViewAttached?.invoke(holder as DefaultViewHolder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder, position: Int) {
        onViewDetached?.invoke(holder as DefaultViewHolder)
    }

}
