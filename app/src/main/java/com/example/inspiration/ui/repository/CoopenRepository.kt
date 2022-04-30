package com.example.inspiration.ui.repository

import com.example.inspiration.base.BaseRepository
import com.example.inspiration.httpUtils.ApiResponse
import com.example.inspiration.httpUtils.RetrofitClient
import com.example.inspiration.httpUtils.UserToken

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.repository
 * @ClassName:      CoopenRepository
 * @Author:         Yan
 * @CreateDate:     2022年04月30日 13:18:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:     //TODO
 */
class CoopenRepository : BaseRepository() {
    private val mApiService = RetrofitClient.service

    /**
     * 登录
     * @param phone 注册的手机号
     */
    suspend fun loginIn(phone : String) : ApiResponse<UserToken> {
        return executeHttp {
            mApiService.loginIn(phone)
        }
    }

    /**
     * 注册
     * @param phone 手机号
     * @param username 用户名
     */
    suspend fun register(phone:String ,username : String ) : ApiResponse<Any>{
        return executeHttp {
            mApiService.register(phone,username)
        }
    }

}