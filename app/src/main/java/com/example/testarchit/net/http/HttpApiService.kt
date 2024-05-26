package com.example.testarchit.net.http

import android.util.Log
import com.example.testarchit.ui.reflow.ReflowFragment

object HttpApiService {
    private const val TAG = "PaxPayHttpService"
    private val mApiService: ApiService = ApiServiceManager.getApiService()

    // 发送请求的方法
    suspend fun <T : ResultResponse> sendRequest(msgType: Int, callback: NetworkCallback<T>) {
        try {
            // 在这里处理发送请求的逻辑
            // 可以使用 mApiService 发送请求，并将回调传递给网络请求的处理逻辑
            val result = mApiService.getConnect()
            Log.w(TAG, "onSuccessResponse-code=" + result.resultCode)
            // 尝试将 result 转换为泛型 T
            val typedResult = result as? T
            if (typedResult != null) {
                callback.onResponse(typedResult)
            } else {
                // 如果转换失败，使用 result 的信息创建一个 ResultResponse 对象，并传递给 onFailureResponse 方法
                callback.onFailureResponse(ResultResponse(result.resultCode, result.message))
            }
        } catch (e: Exception) {
            // 发生异常，调用 onFailureResponse 方法，并传递异常信息
            callback.onFailure(e)
        }
    }
}