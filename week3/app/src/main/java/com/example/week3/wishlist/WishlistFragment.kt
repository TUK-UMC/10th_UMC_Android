package com.example.week3.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week3.R
import com.example.week3.databinding.FragmentWishlistBinding
import com.example.week3.shop.ShopProductData // Shop에서 쓰던 데이터 클래스 임포트

class WishlistFragment : Fragment() {

    // ViewBinding 메모리 누수 방지 패턴
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. ShopProductData를 사용하여 데이터 생성 (통일성)
        val wishlistProductList = arrayListOf(
            ShopProductData(
                R.drawable.home_jordan,
                true,
                "Air Jordan 1 Mid",
                "Men's Shoes",
                "1 Colour",
                "US$125",
                true // isFavorite 상태를 true로 설정
            ),
            ShopProductData(
                R.drawable.shop_socks6,
                true,
                "Nike Everyday Plus Cushioned",
                "Training Ankle Socks(6Pairs)",
                "5 Colours",
                "US$10",
                true // isFavorite 상태를 true로 설정
            )
        )

        // 2. 어댑터 연결 (WishlistAdapter 내부에 ShopProductData를 쓰도록 수정해야 함)
        val wishListAdapter = WishlistAdapter(wishlistProductList)

        binding.wishlistRecyclerview.apply {
            adapter = wishListAdapter
            // 2줄씩 보여주는 그리드 레이아웃 설정
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}