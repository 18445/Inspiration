package com.example.inspiration.httpUtils



import android.media.session.MediaSession
import android.util.Log
import com.example.inspiration.constant.Constant
import com.tencent.mmkv.MMKV
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.nio.charset.StandardCharsets


/**
 * desc   : 自定义拦截器（token添加、token校验）
 * version: 1.0
 */
class TokenInterceptor : Interceptor {
    @Volatile
    var token: String? = null

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request: Request = chain.request()

        //请求头添加token
        val newRequest = checkIgnoreToken(request)
        if (newRequest != null) {
            request = newRequest
        }

        Log.d("requestHeader",request.headers.toString())
        val response = chain.proceed(request)
        Log.d("responseHeader",response.headers.toString())

        //判断token是否失效，若失效则自动登录
//        val newResponse = checkTokenExpired(chain, response)
//        if (newResponse != null) {
//            return newResponse
//        }

        return response
    }

    /**
     * @Description:检查是否忽略token
     * @Param: [request]
     * @Return: boolean
     */
    private fun checkIgnoreToken(request: Request): Request? {

        return try {
            val ignoreToken: Boolean
            val ignoreTokenStr = request.header("ignoreToken")

            ignoreToken = ignoreTokenStr?.toBoolean() ?: true

            if (!ignoreToken) {
                if (token.isNullOrBlank() || token?.trim()?.isNotEmpty() == true ) {
                    token = MMKV.defaultMMKV().decodeString(Constant.Access_USER_TOKEN)
                }

//                val bearerToken = "bearer ${token!!}"
                // 用户 token 没数据
                val bearerToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRoZW50aWNhdGlvbiIsImlzcyI6IlJFRFJPQ0siLCJpYXQiOjE2NTA4NjYzNjEsInVzZXJJRCI6MX0.IahGNpfdCAR2vjO2yrkWlkFDASngkhPJ_YT-qjaLgQEyAAc0G5-exyYB4l1s_tOz8A00cSIVU-wmzrUdExz2bg"

                return  request.newBuilder()
                        .addHeader("Authorization",bearerToken)
                        .build()
            }
            null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * @Description:判断token是否失效，若失效则自动登录
     * @Param: [response]
     * @Return: okhttp3.Response
     */
    private fun checkTokenExpired(chain: Interceptor.Chain, response: okhttp3.Response): okhttp3.Response? {
        try {
            val tokenJson = JSONObject(getResponseInfo(response))
            if (tokenJson.has("errCode")) {
                //TODO TOKEN 过期处理
                if ((tokenJson.optString("message") == "noLogin")) {
                    HttpLoggingInterceptor.Logger {
                        Log.e(it,"TOKEN过期")
                    }
                    val refreshToken = MMKV.defaultMMKV().decodeString(Constant.Refresh_USER_TOKEN)
                    val stringBody = refreshToken?.toRequestBody("application/json;charset=UTF-8".toMediaType())
                    if(refreshToken != null){
                        val body = RequestBody.create("application/json;charset=UTF-8".toMediaType(),refreshToken)

                    val execute: Response<Any> =
                        //TODO 重新登录
//                        RetrofitClient.service.reLogin(body).execute()
                        RetrofitClient.service.getToken().execute()
                    val bodyString = execute.body()!!.toString()
                    val jsonObject = JSONObject(bodyString)
                        //TODO Token 过期
                    if ("401" == jsonObject.optString("code")) {
                        Log.e("checkTokenExpired","token失效，结束重新登录...")
                        val headers = execute.headers()
                        //TODO 重新获得Token
//                        val token: String = MyTools.saveSession(headers)
                        //永久的Token代替
                        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBdXRoZW50aWNhdGlvbiIsImlzcyI6IlJFRFJPQ0siLCJpYXQiOjE2NTE0NjUzMTAsInVzZXJJRCI6MX0.VcH4e6sLyx_32JwdyyOuIIoC-IrhAuyrNQY63Mlx4pbn9ppnTVrS9QJ-b35Z6XqoE0u-xx4rADTIYXNym-Ojow"
                        MMKV.defaultMMKV().encode(Constant.Access_USER_TOKEN, token)

                        //使用新的Token，创建新的请求
                        val newRequest: Request = chain.request().newBuilder()
                            .addHeader("token", token!!)
                            .build()

                        return chain.proceed(newRequest)

                    }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 打印返回消息
     *
     * @param response 返回的对象
     */
    private fun getResponseInfo(response: okhttp3.Response?): String {
        var str = ""
        if (null == response || !response.isSuccessful) {
            return str
        }
        val responseBody = response.body ?: return str
        val contentLength = responseBody.contentLength()
        val source = responseBody.source()
        try {
            // Buffer the entire body.
            source.request(Long.MAX_VALUE)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val buffer = source.buffer
        val charset = StandardCharsets.UTF_8
        if (contentLength != 0L) {
            str = buffer.clone().readString(charset)
        }
        return str
    }
}