package com.example.testarchit.net.http

import okhttp3.OkHttpClient

object ApiServiceManager {
    private const val TAG = "ApiServiceManager"
    private const val baseUrl = "http://192.168.1.11:9092/"
    private lateinit var apiService: ApiService
    private lateinit var okHttpClient: OkHttpClient
}