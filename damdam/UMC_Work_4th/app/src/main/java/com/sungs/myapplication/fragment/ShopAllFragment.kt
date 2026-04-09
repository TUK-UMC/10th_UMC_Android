package com.sungs.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungs.myapplication.R
import com.sungs.myapplication.adapter.ShopProductAdapter
import com.sungs.myapplication.data.ProductData
import com.sungs.myapplication.data.ProductDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ShopAllFragment : Fragment() {

    private val defaultProducts = listOf(
        ProductData("Nike Everyday Plus Cushioned", "Training Crew Socks", "US\$22", R.drawable.img_socks_everyday),
        ProductData("Nike Elite Crew", "Basketball Socks", "US\$18", R.drawable.img_socks_elite),
        ProductData("Air Jordan 1 Mid", "Shoes", "US\$125", R.drawable.img_jordan_1_mid),
        ProductData("Nike Air Force 1 '07", "Shoes", "US\$115", R.drawable.img_air_force_1),
        ProductData("Nike Dunk Low", "Shoes", "US\$115", R.drawable.img_dunk_low)
    )

    private lateinit var adapter: ShopProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rv = RecyclerView(requireContext()).apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            clipToPadding = false
            setPadding(30, 0, 30, 0)
        }

        adapter = ShopProductAdapter(emptyList()) { product ->
            viewLifecycleOwner.lifecycleScope.launch {
                ProductDataStore.toggleFavorite(requireContext(), product.name)
            }
        }
        rv.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            // 초기 데이터 저장
            val existing = ProductDataStore.getShopProducts(requireContext()).first()
            if (existing.isEmpty()) {
                ProductDataStore.saveShopProducts(requireContext(), defaultProducts)
            }

            // 상품 로드
            launch {
                ProductDataStore.getShopProducts(requireContext()).collect { products ->
                    adapter.updateProducts(products)
                }
            }

            // 즐겨찾기 상태 관찰
            launch {
                ProductDataStore.getFavorites(requireContext()).collect { favs ->
                    adapter.updateFavorites(favs)
                }
            }
        }

        return rv
    }
}