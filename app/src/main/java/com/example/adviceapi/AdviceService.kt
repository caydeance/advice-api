package com.example.adviceapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AdviceService {
    @GET("advice/search/{query}")
    //suspend fun searchAdvice(@Path("query") query: String): AdviceCollection

    fun searchAdvice(@Path("query") query: String): Call<AdviceCollection>
}