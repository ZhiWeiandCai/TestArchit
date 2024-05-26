package com.example.testarchit.net.http

import retrofit2.http.GET

// 定义一个用于网络请求的接口ApiService
interface ApiService {

    // 定义一个GET请求方法，用于连接到指定的URL
    // @GET注解指定了相对路径"/connect"
    // suspend关键字表示这是一个挂起函数，可以在协程中调用
    // 函数返回一个ResultResponse对象，表示请求的响应结果
    @GET("/connect")
    suspend fun getConnect(): ResultResponse
}
