package com.example.adviceapi

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adviceapi.databinding.ActivityVentBinding
import android.text.TextWatcher

class VentActivity : AppCompatActivity() {
//    companion object{
//        val EXTRA_VENT = "activity_vent"
//    }



    private lateinit var binding: ActivityVentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVentBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(binding.ventLayout) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        binding.ventSubmitButton.setOnClickListener {
//            val ventWords = binding.ventText.text.toString()
//            val ventIntent = Intent(this, AdviceSlipActivity::class.java)
//
//            ventIntent.putExtra(EXTRA_VENT, ventWords)

            val ventIntent = Intent(this, AdviceSlipListActivity::class.java)

            startActivity(ventIntent)


        }

        val waterView = binding.viewVentActivityWater
        val ventText = binding.textViewVentActivityText

        ventText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val textLength = s?.length ?: 0
                val maxChars = 2510 // The character count at which water is "empty"

                // Calculate the new height percentage (draining effect)
                val factor = (maxChars - textLength).coerceAtLeast(0).toFloat() / maxChars

                // Update the water view's height
                val params = waterView.layoutParams
                params.height = (binding.ventLayout.height * factor).toInt()
                waterView.layoutParams = params

                binding.waterWave.translationX = (Math.sin(textLength.toDouble()*0.67)*50).toFloat()
            }
        })

    }


}