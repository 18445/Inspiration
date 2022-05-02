package com.example.inspiration.ui.repository

import com.example.inspiration.base.BaseRepository
import com.example.inspiration.httpUtils.ApiResponse
import com.example.inspiration.httpUtils.RetrofitClient
import com.example.inspiration.httpUtils.StarList

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.repository
 * @ClassName:      CollectRepository
 * @Author:         Yan
 * @CreateDate:     2022年05月02日 12:41:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:
 */
class CollectRepository : BaseRepository() {
    private val mApiService = RetrofitClient.service

    /**
     * 获得收藏列表
     */
    suspend fun getStarList(id : Int) : ApiResponse<StarList>{
        return executeHttp {
            mApiService.getStarList(id)
        }
    }
}