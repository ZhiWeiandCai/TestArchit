package com.example.testarchit.net.http

import retrofit2.http.GET

interface ApiService {
    @GET("/connect")
    suspend fun getConnect(): ResultResponse
}