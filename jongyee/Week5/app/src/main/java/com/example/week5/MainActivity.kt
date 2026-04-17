package com.example.week5

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.week5.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) changeFragment(HomeFragment())

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

        lifecycleScope.launch {
            sharedViewModel.navigateToDetail.collect {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, DetailFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        lifecycleScope.launch {
            sharedViewModel.navigateToBuyTab.collect {
                binding.mainBottomNav.selectedItemId = R.id.nav_buy
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit()
    }
}
