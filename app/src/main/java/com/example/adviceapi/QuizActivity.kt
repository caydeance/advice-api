package com.example.adviceapi


import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adviceapi.databinding.ActivityQuizBinding
import com.google.gson.Gson


class QuizActivity : AppCompatActivity() {
    companion object{
        val QUIZ_RESULT_QUERY = "life"
    }

    private lateinit var binding: ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var gson = Gson()
        val inputStream = resources.openRawResource(R.raw.quiz)
        val jsonString = inputStream.bufferedReader().use{
            it.readText()
        }


    }




}