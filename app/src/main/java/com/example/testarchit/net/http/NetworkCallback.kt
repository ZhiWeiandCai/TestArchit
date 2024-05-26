package com.example.testarchit.net.http

import android.util.Log
import retrofit2.Response

// 创建抽象类NetworkCallback，泛型T继承ResultResponse
abstract class NetworkCallback<T : ResultResponse> {
    private val tag = "NetworkCallback"

    fun onResponse(resultResponse: T) {
        // 处理成功响应
        onSuccessResponse(resultResponse)
    }

    fun onFailure(p1: Throwable) {
        Log.e(tag, "onFailure==== $p1")
    }

    // 抽象方法，成功响应时调用，传递泛型T的实例
    abstract fun onSuccessResponse(t: T)

    // 抽象方法，失败响应时调用，传递ResultResponse的实例
    abstract fun onFailureResponse(t: ResultResponse)
}