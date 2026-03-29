package com.example.week1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.week1.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEmotionListeners()
    }

    // 리소스 분리 및 반복문
    private fun setupEmotionListeners() {
        val emotions = listOf(
            Triple(binding.ivHappy, binding.tvHappy, R.color.emotion_happy),
            Triple(binding.ivExcited, binding.tvExcited, R.color.emotion_excited),
            Triple(binding.ivNormal, binding.tvNormal, R.color.emotion_normal),
            Triple(binding.ivAnxious, binding.tvAnxious, R.color.emotion_anxious),
            Triple(binding.ivAngry, binding.tvAngry, R.color.emotion_angry)
        )

        emotions.forEach { (imageView, textView, colorRes) ->
            imageView.setOnClickListener {

                //모든 텍스트 색상 초기화
                emotions.forEach { (_, tv, _) ->
                    tv.setTextColor(ContextCompat.getColor(this, R.color.gray_default))
                }
                //클릭한 텍스트 색만 변경
                textView.setTextColor(ContextCompat.getColor(this, colorRes))
            }
        }
    }
}