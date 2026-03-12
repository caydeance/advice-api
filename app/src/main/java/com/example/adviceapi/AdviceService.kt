package com.example.adviceapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AdviceService {
    @GET("advice")
    //suspend fun searchAdvice(@Path("query") query: String): AdviceCollection

    fun searchAdvice(@Path("query") query: String): Call<AdviceCollection>
}