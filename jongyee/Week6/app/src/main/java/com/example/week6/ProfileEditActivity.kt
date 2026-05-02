package com.example.week6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.week6.databinding.ActivityProfileEditBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
