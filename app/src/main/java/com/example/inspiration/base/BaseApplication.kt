package com.example.inspiration.base
import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import com.example.inspiration.httpUtils.ApiService
import com.tencent.mmkv.MMKV

/**
 *
 * @ClassName:      MyApplication
 * @Author:         Yan
 * @CreateDate:     12点30分
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    Application 管理全局变量
 */

class BaseApplication : Application() {

    companion object {
        lateinit var instance: Application
    }


    override fun onCreate() {
        super.onCreate()

        instance = this

        val rootDir = MMKV.initialize(this)
        Log.d(TAG, "mmkv_root------:${rootDir}")

    }

    private var apiService : ApiService? = null


}


