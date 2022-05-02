package com.example.inspiration.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inspiration.extension.StateLiveData
import com.example.inspiration.httpUtils.StarList
import com.example.inspiration.ui.repository.CollectRepository
import kotlinx.coroutines.launch

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.viewModel
 * @ClassName:      CollectViewModel
 * @Author:         Yan
 * @CreateDate:     2022年05月02日 12:45:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    CollectViewModel
 */
class CollectViewModel : ViewModel() {

    private val collectRepository by lazy {
        CollectRepository()
    }

    val starList : StateLiveData<StarList> = StateLiveData()

    fun getStarList(id : Int){
        viewModelScope.launch {
            starList.value = collectRepository.getStarList(id)
            Log.d("starList",starList.value.toString())
        }
    }
}