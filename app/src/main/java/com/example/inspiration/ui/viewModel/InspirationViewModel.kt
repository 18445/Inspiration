package com.example.inspiration.ui.viewModel

import android.util.Log
import android.util.SparseArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inspiration.extension.StateLiveData
import com.example.inspiration.httpUtils.ColorList
import com.example.inspiration.httpUtils.InspirationDetail
import com.example.inspiration.httpUtils.InspirationHome
import com.example.inspiration.httpUtils.InspirationList
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

    var mThemes : MutableLiveData<SparseArray<String>> = MutableLiveData(SparseArray())
    val mInspirationHome = StateLiveData<List<InspirationHome>>()
    val mInspirationList = StateLiveData<List<InspirationList>>()
    val mInspirationDetailSet = SparseArray<StateLiveData<InspirationDetail>>()

    fun getInspirationHome(){
        viewModelScope.launch {
            mInspirationHome.value = inspirationRepository.getInspirationHome()
            Log.d("mInspirationHome",mInspirationHome.value.toString())
        }
    }

    fun getInspirationList(id : String){
        viewModelScope.launch {
            mInspirationList.value = inspirationRepository.getInspirationList(id)
            Log.d("mInspirationList",mInspirationList.value.toString())
        }
    }

    fun getInspirationDetail(id : String){
        mInspirationDetailSet.put(id.toInt(),StateLiveData())
        viewModelScope.launch {
            mInspirationDetailSet[id.toInt()].value = inspirationRepository.getInspirationIdea(id)
            Log.d("mInspirationDetail",mInspirationDetailSet[id.toInt()].value.toString())
        }
    }

}