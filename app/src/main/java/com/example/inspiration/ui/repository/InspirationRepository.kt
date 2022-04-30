package com.example.inspiration.ui.repository

import com.example.inspiration.base.BaseRepository
import com.example.inspiration.httpUtils.ApiResponse
import com.example.inspiration.httpUtils.InspirationHome
import com.example.inspiration.httpUtils.RetrofitClient

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.repository
 * @ClassName:      InspirationRepository
 * @Author:         Yan
 * @CreateDate:     2022年04月30日 22:49:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:     //TODO
 */
class InspirationRepository : BaseRepository() {
    private val mApiService = RetrofitClient.service

    suspend fun getInspirationHome() : ApiResponse<List<InspirationHome>>{
        return executeHttp {
            mApiService.getInspirationHome()
        }
    }
}