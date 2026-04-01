package com.example.week3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week3.R
import com.example.week3.databinding.FragmentShopBinding
import com.example.week3.shop.ShopProductAdapter // 수정된 어댑터 임포트
import com.example.week3.shop.ShopProductData

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        // 1. 데이터 리스트
        val shopProductList = arrayListOf(
            ShopProductData(R.drawable.shop_socks6, false, "Nike Everyday Plus Cushioned", "Training Ankle Socks(6Pairs)", "5 Colours", "US$10", false),
            ShopProductData(R.drawable.shop_elitesocks, false, "Nike Elite Crew", "Basketball Socks", "7 Colours", "US$16", false),
            ShopProductData(R.drawable.home_airforce, true, "Nike Air Force 1'07", "Women's Shoes", "5 Colours", "US$115", false),
            ShopProductData(R.drawable.shop_jodan1, true, "Jordan Essentials", "Mens's Shoes", "2 Colours", "US$115", false
            )
        )

        // 2. 샵 전용 어댑터로 초기화
        val productAdapter = ShopProductAdapter(shopProductList) { product ->
            Toast.makeText(requireContext(), "${product.name} 클릭됨!", Toast.LENGTH_SHORT).show()
        }

        // 3. 리사이클러뷰 설정
        binding.shopRecyclerview.apply { //
            adapter = productAdapter
            layoutManager = GridLayoutManager(requireContext(), 2) //GridLayoutManager 사용
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}