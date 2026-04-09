package com.sungs.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sungs.myapplication.fragment.ShopAllFragment
import com.sungs.myapplication.fragment.ShopSaleFragment
import com.sungs.myapplication.fragment.ShopTopsFragment

class ShopPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabs = listOf(
        "전체" to { ShopAllFragment() },
        "Tops & T-Shirts" to { ShopTopsFragment() },
        "Sale" to { ShopSaleFragment() }
    )

    val tabTitles get() = tabs.map { it.first }

    override fun getItemCount(): Int = tabs.size

    override fun createFragment(position: Int): Fragment = tabs[position].second()
}