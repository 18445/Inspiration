package com.example.inspiration.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inspiration.extension.StateLiveData
import com.example.inspiration.httpUtils.InspirationHome
import com.example.inspiration.ui.repository.InspirationRepository
import kotlinx.coroutines.launch

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.viewModel
 * @ClassName:      InspirationViewModel
 * @Author:         Yan
 * @CreateDate:     2022年04月30日 22:51:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    InspirationViewModel
 */
class InspirationViewModel : ViewModel() {

    private val inspirationRepository by lazy {
        InspirationRepository()
    }

    val mInspirationHome = StateLiveData<List<InspirationHome>>()

    fun getInspirationHome(){
        viewModelScope.launch {
            mInspirationHome.value = inspirationRepository.getInspirationHome()
            Log.d("mInspirationHome",mInspirationHome.value.toString())
        }
    }

}