package com.example.inspiration.httpUtils

import androidx.viewbinding.BuildConfig
import com.example.inspiration.constant.HttpConstant
import com.example.inspiration.constant.HttpConstant.DEFAULT_READ_TIME
import com.example.inspiration.constant.HttpConstant.DEFAULT_WRITE_TIME
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * @ClassName:      RetrofitClient
 * @Author:         Yan
 * @CreateDate:     12点52分
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    RetrofitClient
 */
object RetrofitClient {

    //请求的地址
    const val BASE_URL = "http://redrock.udday.cn:8888"

    //retrofit对象
    private var retrofit: Retrofit? = null

    //请求的api，可以根据不同的场景设置多个
    val service: ApiService by lazy {
        getRetrofit().create(ApiService::class.java)
    }

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            synchronized(this){
                if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                }
            }
        }
        return retrofit!!
    }

    /**
     * 获取 OkHttpClient
     */
    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
            .connectTimeout(HttpConstant.DEFAULT_CONNECT_TIME, TimeUnit.SECONDS)//连接超时时间
            .writeTimeout(DEFAULT_WRITE_TIME, TimeUnit.SECONDS)//设置写操作超时时间
            .readTimeout(DEFAULT_READ_TIME, TimeUnit.SECONDS)//设置读操作超时时间
            .addInterceptor(getHttpLoggingInterceptor())

        return builder.build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }
        return logging
    }

}