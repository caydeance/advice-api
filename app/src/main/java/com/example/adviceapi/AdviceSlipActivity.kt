package com.example.adviceapi
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AdviceSlipActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advice_slip)
    }
    val adviceService = RetrofitHelper.getInstance().create(AdviceService::class.java)
    val adviceCall = adviceService.getRandomAdvice()
}