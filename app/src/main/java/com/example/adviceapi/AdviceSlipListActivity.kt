package com.example.adviceapi
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adviceapi.databinding.ActivityAdviceListBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

class AdviceSlipListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdviceListBinding
    private lateinit var adapter: AdviceSlipAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAdviceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val quizResultQuery = intent.getStringExtra("QUIZ_RESULT_QUERY") ?: "life"
        val adviceService = RetrofitHelper.getInstance().create(AdviceService::class.java)
        val adviceCall = adviceService.searchAdvice(quizResultQuery)


        adviceCall.enqueue(object : Callback<AdviceCollection> {
            override fun onResponse(
                call: Call<AdviceCollection?>,
                response: Response<AdviceCollection>
            ) {
                val slipList = response.body()?.slips
                val randomThreeSlips = slipList?.shuffled()?.take(3) ?: emptyList()
                val recyclerView: RecyclerView = findViewById(R.id.recyclerView_adviceSlipList)
                recyclerView.layoutManager = LinearLayoutManager(this@AdviceSlipListActivity)

                adapter = AdviceSlipAdapter(randomThreeSlips)
                recyclerView.adapter = adapter
            }

            override fun onFailure(
                call: Call<AdviceCollection?>,
                t: Throwable
            ) {
                Log.d("AdviceSlipList", "onFailure: ${t.message}")
            }
        })
    }
}
