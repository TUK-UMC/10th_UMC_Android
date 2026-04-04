package com.example.week3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels // 중요: 이 임포트가 있어야 by activityViewModels() 사용 가능
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week3.R
import com.example.week3.databinding.FragmentShopBinding
import com.example.week3.shop.SharedViewModel
import com.example.week3.shop.ShopProductAdapter
import com.example.week3.shop.ShopProductData

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    // 1. Activity와 공유하는 ViewModel 선언
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        // 2. 데이터 리스트 준비
        val shopProductList = arrayListOf(
            ShopProductData(R.drawable.shop_socks6, false, "Nike Everyday Plus Cushioned", "Training Ankle Socks(6Pairs)", "5 Colours", "US$10", false),
            ShopProductData(R.drawable.shop_elitesocks, false, "Nike Elite Crew", "Basketball Socks", "7 Colours", "US$16", false),
            ShopProductData(R.drawable.home_airforce, true, "Nike Air Force 1'07", "Women's Shoes", "5 Colours", "US$115", false),
            ShopProductData(R.drawable.shop_jodan1, true, "Jordan Essentials", "Mens's Shoes", "2 Colours", "US$115", false)
        )

        // 3. 어댑터 초기화 (onFavoriteClick과 onItemClick 콜백 구현)
        val productAdapter = ShopProductAdapter(
            productList = shopProductList,
            onFavoriteClick = { product ->
                // 하트 클릭 시 SharedViewModel의 리스트 업데이트 요청
                sharedViewModel.toggleWishlist(product)
            },
            onItemClick = { product ->
                // 상품 클릭 시 토스트 메시지
                Toast.makeText(requireContext(), "${product.name} 클릭됨!", Toast.LENGTH_SHORT).show()
            }
        )

        // 4. 리사이클러뷰 설정
        binding.shopRecyclerview.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}