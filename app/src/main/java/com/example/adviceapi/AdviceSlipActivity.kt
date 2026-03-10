package com.example.adviceapi

class AdviceSlipActivity {
    val adviceService = RetrofitHelper.getInstance().create(AdviceService::class.java)
    val adviceCall = adviceService.getRandomAdvice()
}