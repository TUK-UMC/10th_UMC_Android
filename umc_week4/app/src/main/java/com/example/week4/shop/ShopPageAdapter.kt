package com.example.week4.shop

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.week4.fragment.ShopFragment

class ShopPageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabs = listOf(
        "전체" to { ShopFragment() },
        "Tops & T-Shirts" to { TopFragment() },
        "Sale" to { SaleFragment() }
    )

    val tabTitles get() = tabs.map { it.first }

    override fun getItemCount(): Int = tabs.size

    override fun createFragment(position: Int): Fragment = tabs[position].second()
}