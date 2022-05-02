package com.example.inspiration.httpUtils


import retrofit2.Call
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
     * 得到灵感首页的list、
     */
    @GET("/idea/idea_detail_list")
    suspend fun getInspirationList(@Query("idea_id") id: String) : ApiResponse<List<InspirationList>>

    /**
     * 得到灵感首页的详细
     */
    @GET("/idea/idea_detail")
    suspend fun getInspirationIdea(@Query("idea_detail_id") id: String) : ApiResponse<InspirationDetail>

    /**
     * 收藏
     */
    @Headers("Content-type:application/json;charset=UTF-8;","ignoreToken:false")
    @FormUrlEncoded
    @POST("/star/star")
    suspend fun starStar(@Field("shade_id") id : String,@Field("name")name:String) : ApiResponse<Any>

    /**
     * 删除收藏
     */
    @Headers("Content-type:application/json;charset=UTF-8;","ignoreToken:false")
    @FormUrlEncoded
    @POST("/star/delete_star")
    suspend fun deleteStar(@Field("star_id") id : String) : ApiResponse<Any>

    /**
     * 得到收藏列表
     */
    @Headers("Content-type:application/json;charset=UTF-8;","ignoreToken:false")
    @GET("/star/star_list")
    suspend fun getStarList(@Query("page") page : Int) : ApiResponse<StarList>

    /**
     * 重新获得Token
     */
    @GET("/user/long_login")
    fun getToken() : Call<Any>



}