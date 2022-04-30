package com.example.inspiration.ui.viewModel
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inspiration.extension.StateLiveData
import com.example.inspiration.httpUtils.UserToken
import com.example.inspiration.model.LoginModel
import com.example.inspiration.model.RegisterModel
import com.example.inspiration.ui.repository.CoopenRepository
import kotlinx.coroutines.launch

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.ViewModel
 * @ClassName:      com.example.inspiration.ui.ViewModel.CoopenViewModel
 * @Author:         Yan
 * @CreateDate:     2022年04月30日 13:16:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:     //TODO
 */
class CoopenViewModel : ViewModel() {

    private val coopenRepository by lazy {
        CoopenRepository()
    }

    val userToken = StateLiveData<UserToken>()
    val registerInfo = StateLiveData<Any>()

    private val loginModelObservable : ObservableField<LoginModel> by lazy {
        val observable = ObservableField<LoginModel>()
        observable.set(LoginModel(""))
        observable
    }

    private val registerModelObservable : ObservableField<RegisterModel> by lazy {
        val observable = ObservableField<RegisterModel>()
        observable.set(RegisterModel("",""))
        observable
    }

    //双向绑定确定用户登录信息
    fun getUserLoginPhone() : String? {
        Log.d("getUserLoginPhone",loginModelObservable.get()?.phone.toString())
        return loginModelObservable.get()?.phone
    }
    fun setUserLoginPhone(value : String){
        Log.d("setUserLoginPhone",value)
        loginModelObservable.get()?.phone = value
    }

    //双向绑定确定用户注册信息
    fun getUserRegisterPhone() : String?{
        return registerModelObservable.get()?.phone
    }
    fun setUserRegisterPhone(value :String){
        registerModelObservable.get()?.phone = value
    }
    fun getUserRegisterUsername() : String?{
        return registerModelObservable.get()?.username
    }
    fun setUserRegisterUsername(value :String){
        registerModelObservable.get()?.username = value
    }

    fun loginIn(phone : String){
        viewModelScope.launch {
            userToken.value = coopenRepository.loginIn(phone)
            Log.d("userToken",userToken.value.toString())
        }
    }

    fun register(phone : String,userName : String){
        viewModelScope.launch {
            registerInfo.value = coopenRepository.register(phone,userName)
            Log.d("registerInfo",registerInfo.toString())
        }
    }
}