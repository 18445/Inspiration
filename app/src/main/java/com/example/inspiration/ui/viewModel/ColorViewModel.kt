package com.example.inspiration.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inspiration.extension.StateLiveData
import com.example.inspiration.httpUtils.ColorDetail
import com.example.inspiration.httpUtils.ColorList
import com.example.inspiration.httpUtils.ColorPage
import com.example.inspiration.ui.repository.ColorRepository
import kotlinx.coroutines.launch

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
    val colorList = StateLiveData<ColorList>()
    val colorDetail = StateLiveData<ColorDetail>()

    fun getColorPageId(){
        viewModelScope.launch {
            colorPage.value = colorRepository.getColorPageId()
            Log.d("colorPage",colorPage.value.toString())
        }
    }

    fun getColorList(id : Int){
        viewModelScope.launch {
            colorList.value = colorRepository.getColorList(id)
        }
    }

    fun getColorDetail(id : Int){
        viewModelScope.launch {
            colorDetail.value = colorRepository.getColorDetail(id)
        }
    }
}