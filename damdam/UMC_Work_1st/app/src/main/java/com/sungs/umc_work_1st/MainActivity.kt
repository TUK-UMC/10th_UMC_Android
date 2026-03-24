package com.sungs.umc_work_1st

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sungs.umc_work_1st.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ivEmotionHappy.setOnClickListener {
            resetTextColors()
            val happyColor = ContextCompat.getColor(this, R.color.emotion_happy_bg)
            binding.tvEmotionHappy.setTextColor(happyColor)
        }

        binding.ivEmotionGlad.setOnClickListener {
            resetTextColors()
            val gladColor = ContextCompat.getColor(this, R.color.emotion_glad_bg)
            binding.tvEmotionGlad.setTextColor(gladColor)
        }

        binding.ivEmotionSoso.setOnClickListener {
            resetTextColors()
            val sosoColor = ContextCompat.getColor(this, R.color.emotion_soso_bg)
            binding.tvEmotionSoso.setTextColor(sosoColor)
        }

        binding.ivEmotionSad.setOnClickListener {
            resetTextColors()
            val sadColor = ContextCompat.getColor(this, R.color.emotion_sad_bg)
            binding.tvEmotionSad.setTextColor(sadColor)
        }

        binding.ivEmotionAngry.setOnClickListener {
            resetTextColors()
            val angryColor = ContextCompat.getColor(this, R.color.emotion_angry_bg)
            binding.tvEmotionAngry.setTextColor(angryColor)
        }
    }
    private fun resetTextColors() {
        val defaultColor = ContextCompat.getColor(this, R.color.text_default)
        binding.tvEmotionHappy.setTextColor(defaultColor)
        binding.tvEmotionGlad.setTextColor(defaultColor)
        binding.tvEmotionSoso.setTextColor(defaultColor)
        binding.tvEmotionSad.setTextColor(defaultColor)
        binding.tvEmotionAngry.setTextColor(defaultColor)
    }
}