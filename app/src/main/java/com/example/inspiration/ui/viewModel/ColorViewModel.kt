package com.example.inspiration.ui.viewModel

import android.util.Log
import android.util.SparseArray
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inspiration.extension.StateLiveData
import com.example.inspiration.httpUtils.ColorDetail
import com.example.inspiration.httpUtils.ColorList
import com.example.inspiration.httpUtils.ColorPage
import com.example.inspiration.ui.repository.ColorRepository
import kotlinx.coroutines.launch
import java.sql.Statement

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.viewModel
 * @ClassName:      ColorViewModel
 * @Author:         Yan
 * @CreateDate:     2022年04月30日 16:28:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    色谱的ViewModel
 */
class ColorViewModel : ViewModel(){

    private val colorRepository by lazy {
        ColorRepository()
    }


    val colorPage = StateLiveData<ColorPage>()
    val colorDetail = StateLiveData<ColorDetail>()
    val colorListSet = SparseArray<StateLiveData<ColorList>>()

    fun getColorPageId(){
        viewModelScope.launch {
            colorPage.value = colorRepository.getColorPageId()
            Log.d("colorPage",colorPage.value.toString())
        }
    }

    fun getColorList(id : String){
        colorListSet.put(id.toInt(),StateLiveData())
        viewModelScope.launch {
            colorListSet[id.toInt()].value = colorRepository.getColorList(id)
            Log.d("colorList",colorListSet[id.toInt()].value.toString())
        }
    }

    fun getColorDetail(id : String){
        viewModelScope.launch {
            colorDetail.value = colorRepository.getColorDetail(id)
            Log.d("colorDetail",colorDetail.value.toString())
        }
    }
}