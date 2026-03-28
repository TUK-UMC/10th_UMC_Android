package com.example.umc_ian_week2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.umc_ian_week2.databinding.ActivityMainBinding
import com.example.umc_ian_week2.fragment.BagFragment
import com.example.umc_ian_week2.fragment.HomeFragment
import com.example.umc_ian_week2.fragment.ProfileFragment
import com.example.umc_ian_week2.fragment.ShopFragment
import com.example.umc_ian_week2.fragment.WishlistFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }
        //bottom
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment())
                R.id.nav_shop -> replaceFragment(ShopFragment())
                R.id.nav_wishlist -> replaceFragment(WishlistFragment())
                R.id.nav_bag -> replaceFragment(BagFragment())
                R.id.nav_profile -> replaceFragment(ProfileFragment())
                else -> return@setOnItemSelectedListener false
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}