package com.example.inspiration.ui.repository

import com.example.inspiration.base.BaseRepository
import com.example.inspiration.httpUtils.*

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.repository
 * @ClassName:      ColorRepositroy
 * @Author:         Yan
 * @CreateDate:     2022年04月30日 16:28:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    色谱界面的Repository
 */
class ColorRepository : BaseRepository(){

    private val mApiService = RetrofitClient.service

    /**
     * 获得颜色pageId
     *
     */
    suspend fun getColorPageId() : ApiResponse<ColorPage> {
        return executeHttp {
            mApiService.getColorPageId()
        }
    }

    /**
     * 获得每一页颜色列表
     */
    suspend fun getColorList(id:String) : ApiResponse<ColorList>{
        return executeHttp {
            mApiService.getColorList(id)
        }
    }

    /**
     * 获得颜色的详细页
     */
    suspend fun getColorDetail(id:String) : ApiResponse<ColorDetail>{
        return executeHttp {
            mApiService.getColorDetail(id)
        }
    }
}