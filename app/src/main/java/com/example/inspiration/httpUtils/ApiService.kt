package com.example.inspiration.httpUtils


import retrofit2.http.*

/**
 *
 * @ClassName:      ApiService
 * @Author:         Yan
 * @CreateDate:     12点52分
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    网络请求API
 */
interface ApiService {

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(@Field("phone_number") phone : String,@Field("name") name:String) : ApiResponse<Any>

    /**
     * 登录并获得Token
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun loginIn(@Field("phone_number") phone : String) : ApiResponse<UserToken>



}