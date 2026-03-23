package com.sungs.umc_work_1st

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
            binding.tvEmotionHappy.setTextColor(Color.argb(255, 255, 239, 182))
        }
        binding.ivEmotionGlad.setOnClickListener {
            binding.tvEmotionGlad.setTextColor(Color.argb(255, 206, 231, 245))
        }
        binding.ivEmotionSoso.setOnClickListener {
            binding.tvEmotionSoso.setTextColor(Color.argb(255, 190, 195, 237))
        }
        binding.ivEmotionSad.setOnClickListener {
            binding.tvEmotionSad.setTextColor(Color.argb(255, 177, 211, 185))
        }
        binding.ivEmotionAngry.setOnClickListener {
            binding.tvEmotionAngry.setTextColor(Color.argb(255, 235, 139, 139))
        }
    }
}