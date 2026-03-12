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
    private var query: String = ""

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


        val quizScore = intent.getStringExtra(QuizActivity.EXTRA_QUIZ_SCORE)?.toInt() ?: 0
        val ventTextLen = intent.getStringExtra(VentActivity.EXTRA_VENT_TEXTLEN)?.toInt() ?: 0

        when {
            quizScore >= 31 -> query = "a"
            quizScore in 23..30 -> query = "e"
            quizScore in 16..22 -> query = "i"
            quizScore in 9..15 -> query = "o"
            quizScore in 0..8 -> query = "u"
        }

        when {
            ventTextLen > 2000 -> query = "a"
            ventTextLen in 1600..2000 -> query = "e"
            ventTextLen in 1200..1599 -> query = "i"
            ventTextLen in 800..1999 -> query = "o"
            ventTextLen in 0..799 -> query = "u"
        }


        val adviceService = RetrofitHelper.getInstance().create(AdviceService::class.java)
        val adviceCall = adviceService.searchAdvice(query)

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
