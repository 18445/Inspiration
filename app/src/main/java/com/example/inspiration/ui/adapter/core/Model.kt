package com.example.inspiration.ui.adapter.core

/**
 *
 * @ClassName:      Model
 * @Author:         Yan
 * @CreateDate:     17点42分
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    SameModel 子 Model 用于差分判断
 */


interface SameModel{
    var uniqueId : String
    fun <T: SameModel> isSameModelAs(model : T) : Boolean{
        return this.uniqueId == model.uniqueId
    }
    fun <T: SameModel> isContentTheSameAs(model : T) : Boolean{
        return this == model
    }
    fun <T: SameModel> getChangePayload(newItem : T) : Any? = null
}


