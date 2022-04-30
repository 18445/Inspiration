package com.example.inspiration.ui.adapter.core

import android.annotation.SuppressLint
import android.util.SparseArray
import androidx.lifecycle.GenericLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 *
 * @ProjectName:    ToDo
 * @Package:        com.example.todo.ui.adapter.core
 * @ClassName:      IAdapter
 * @Author:         Yan
 * @CreateDate:     2022年04月20日 00:06:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    IAdapter 生命周期的回调
 */
interface IAdapter<VM> {
    fun getItem(position : Int) : VM?
}

@SuppressLint("RestrictedApi")
interface LifecycleAdapter : GenericLifecycleObserver{
    val arrayLifeObservers : SparseArray<(source : LifecycleOwner,event : Lifecycle.Event) -> Boolean>

    fun registerLifeObserver(key : Int,observer : (LifecycleOwner,Lifecycle.Event) -> Boolean){
        if(arrayLifeObservers.indexOfKey(key) <0)
            arrayLifeObservers.put(key,observer)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        (0 until arrayLifeObservers.size()).forEach {
            arrayLifeObservers.valueAt(it).invoke(source, event)
        }
        if(event == Lifecycle.Event.ON_DESTROY){
            arrayLifeObservers.clear()
        }
    }

}