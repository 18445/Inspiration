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

    /**
     * 获得颜色pageId
     */
    @GET("/color/page")
    suspend fun getColorPageId() : ApiResponse<ColorPage>

    /**
     * 得到每一页的颜色
     */
    @GET("/color/color_list")
    suspend fun getColorList(@Query("theme_id") id :String) : ApiResponse<ColorList>

    /**
     * 得到颜色的详细页
     */
    @GET("/color/color_detail")
    suspend fun getColorDetail(@Query("color_detail_id") id: String): ApiResponse<ColorDetail>

    /**
     * 得到灵感界面的首页信息
     */
    @GET("/idea/idea")
    suspend fun getInspirationHome() : ApiResponse<List<InspirationHome>>

    /**
     * 得到灵感首页的具体信息
     */
//    @GET("/idea/idea_detail_list")

}