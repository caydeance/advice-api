package com.example.adviceapi

import retrofit2.Call
import retrofit2.http.GET

interface AdviceService {
    @GET("advice")
    fun getAdvice() : Call<Advice>
}