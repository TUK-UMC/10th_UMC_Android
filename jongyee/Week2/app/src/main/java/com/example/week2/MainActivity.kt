package com.example.week2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.week2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "LIFE_QUIZ"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate")

        if (savedInstanceState == null) {
            changeFragment(HomeFragment())
        }
        //하단 바 클릭
        binding.mainBottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> changeFragment(HomeFragment())
                R.id.nav_buy -> changeFragment(BuyFragment())
                R.id.nav_wishlist -> changeFragment(WishlistFragment())
                R.id.nav_bag -> changeFragment(BagFragment())
                R.id.nav_profile -> changeFragment(ProfileFragment())
            }
            true
        }
    }

    //프래그먼트 전환
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    //생명주기 로그
    override fun onStart() { super.onStart(); Log.d(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume") }
    override fun onPause() { super.onPause(); Log.d(TAG, "onPause") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy") }
    override fun onRestart() { super.onRestart(); Log.d(TAG, "onRestart") }
}