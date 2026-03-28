package com.sungs.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sungs.myapplication.databinding.ActivityMainBinding
import com.sungs.myapplication.fragment.BagFragment
import com.sungs.myapplication.fragment.HomeFragment
import com.sungs.myapplication.fragment.ProfileFragment
import com.sungs.myapplication.fragment.ShopFragment
import com.sungs.myapplication.fragment.WishlistFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

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