package com.example.testarchit.net.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 定义一个单例对象，用于管理API服务和HTTP客户端
object ApiServiceManager {
    private const val TAG = "ApiServiceManager" // 用于日志的标签
    private const val DEFAULT_BASE_URL = "http://192.168.43.173:9092/" // 默认的Base URL
    private var currentBaseUrl: String = DEFAULT_BASE_URL // 当前的Base URL，可以动态修改

    // 使用lazy初始化OkHttpClient实例，保证只在第一次使用时创建
    private val okHttpClient: OkHttpClient by lazy {
        createApiClient()
    }

    // 使用volatile和双重检查锁定确保线程安全的apiService实例
    @Volatile
    private var apiService: ApiService? = null

    // 创建OkHttpClient实例，并添加日志拦截器
    private fun createApiClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 设置日志级别为BASIC
        }
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    // 创建ApiService实例，使用当前的Base URL
    private fun createApiService(baseUrl: String): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
            .build()
        return retrofit.create(ApiService::class.java)
    }

    // 获取ApiService实例，懒初始化并确保线程安全
    fun getApiService(): ApiService {
        return apiService ?: synchronized(this) {
            apiService ?: createApiService(currentBaseUrl).also { apiService = it }
        }
    }

    // 设置新的Base URL，并重新创建ApiService实例
    fun setBaseUrl(newBaseUrl: String) {
        currentBaseUrl = newBaseUrl
        synchronized(this) {
            apiService = createApiService(newBaseUrl)
        }
    }
}
