package com.example.inspiration.extension

import androidx.lifecycle.Observer
import com.example.inspiration.httpUtils.*

/**
 *
 * @ClassName:      IStateObserver
 * @Author:         Yan
 * @CreateDate:     12点33分
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:
 */
abstract class IStateObserver <T> : Observer<ApiResponse<T>> {
    override fun onChanged(apiResponse: ApiResponse<T>?) {
        when (apiResponse) {
            is ApiSuccessResponse -> onSuccess(apiResponse.response)
            is ApiEmptyResponse -> onDataEmpty()
            is ApiFailedResponse -> onFailed(apiResponse.code, apiResponse.msg)
            is ApiErrorResponse -> onError(apiResponse.throwable)
        }
        onComplete()
    }

    abstract fun onSuccess(data: T)

    abstract fun onDataEmpty()

    abstract fun onError(e: Throwable)

    abstract fun onFailed(errorCode: Int?, errorMsg: String?)

    abstract fun onComplete()
}