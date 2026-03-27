package com.sungs.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sungs.myapplication.databinding.ActivityMainBinding
import com.sungs.myapplication.fragment.BagFragment
import com.sungs.myapplication.fragment.HomeFragment
import com.sungs.myapplication.fragment.ProfileFragment
import com.sungs.myapplication.fragment.ShopFragment
import com.sungs.myapplication.fragment.WishlistFragment

class MainActivity : AppCompatActivity() {

    // 뷰바인딩 세팅
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // HomeFragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        // 바텀 네비게이션 탭 클릭 이벤트 처리
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    true
                }
                R.id.nav_shop -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ShopFragment())
                        .commit()
                    true
                }
                R.id.nav_wishlist -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, WishlistFragment())
                        .commit()
                    true
                }
                R.id.nav_bag -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, BagFragment())
                        .commit()
                    true
                }
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}