package com.example.inspiration.httpUtils
import java.io.*

/**
 *
 * @ClassName:      Response
 * @Author:         Yan
 * @CreateDate:     12点33分
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    Data 数据
 */

open class ApiResponse<out T>(
    open val data: T? = null,
    open val code: Int? = null,
    open val msg: String? = null,
    open val error: Throwable? = null,
) : Serializable {
    val isSuccess: Boolean
        get() = code == 0 || code == 200
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val response: T) : ApiResponse<T>(data = response)

data class ApiFailedResponse<T>(override val code: Int?, override val msg: String?) : ApiResponse<T>(code = code, msg = msg)

data class ApiErrorResponse<T>(val throwable: Throwable) : ApiResponse<T>(error = throwable)


data class UserToken(
    val refreshToken: String,
    val token: String
)

