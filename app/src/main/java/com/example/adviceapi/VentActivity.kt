package com.example.adviceapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adviceapi.databinding.ActivityVentBinding

class VentActivity : AppCompatActivity() {
    companion object{
        val EXTRA_VENT = "activity_vent"
    }



    private lateinit var binding: ActivityVentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.ventLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ventSubmitButton.setOnClickListener {
//            val ventWords = binding.ventText.text.toString()
//            val ventIntent = Intent(this, AdviceSlipActivity::class.java)
//
//            ventIntent.putExtra(EXTRA_VENT, ventWords)

            val ventIntent = Intent(this, AdviceSlipActivity::class.java)

            startActivity(ventIntent)


        }

    }


}