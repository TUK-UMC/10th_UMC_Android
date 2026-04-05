package com.example.week4.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week3.shop.DataStoreManager // 패키지 경로 확인 필요!
import com.example.week4.databinding.FragmentShopBinding
import com.example.week4.shop.ShopProductAdapter
import com.example.week4.shop.ShopProductData
import kotlinx.coroutines.launch
import com.example.week4.R
import com.example.week4.shop.SharedViewModel

class ShopFragment : Fragment() {
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var dataStoreManager: DataStoreManager

    // 어댑터를 나중에 초기화하기 위해 lateinit 사용
    private lateinit var productAdapter: ShopProductAdapter

    private val currentProductList = mutableListOf<ShopProductData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. DataStore 매니저 초기화
        dataStoreManager = DataStoreManager(requireContext())

        // 2. 리사이클러뷰 및 어댑터 설정
        initRecyclerView()

        // 3. DataStore 관찰 시작
        observeDataStore()
    }

    private fun initRecyclerView() {
        productAdapter = ShopProductAdapter(
            productList = currentProductList,
            onFavoriteClick = { product ->
                // 1. 위시리스트 ViewModel 업데이트 (기존 코드)
                sharedViewModel.toggleWishlist(product)

                // 2. 중요: 현재 리스트의 바뀐 상태를 DataStore에 저장 (비동기)
                viewLifecycleOwner.lifecycleScope.launch {
                    dataStoreManager.saveProductList(currentProductList)
                }
            },
            onItemClick = { product ->
                Toast.makeText(context, "${product.name} 클릭됨!", Toast.LENGTH_SHORT).show()
            }
        )

        binding.shopRecyclerview.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun observeDataStore() {
        // 비동기로 DataStore의 데이터를 감시
        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.getProductList().collect { products ->
                if (products.isEmpty()) {
                    // 데이터가 하나도 없다면 최초 1회 더미 데이터를 저장
                    saveInitialDummyData()
                } else {
                    // 데이터가 들어오면 어댑터의 updateData 함수를 호출하여 화면을 갱신
                    productAdapter.updateData(products)
                }
            }
        }
    }

    private suspend fun saveInitialDummyData() {
        // 요구사항: 최초 진입 시 최신 상품 등의 더미 데이터를 DataStore에 저장
        val dummyList = listOf(
            ShopProductData(R.drawable.img_shop_socks6, false, "Nike Everyday Plus", "Training Socks", "5 Colours", "US$10"),
            ShopProductData(R.drawable.img_shop_elitesocks, false, "Nike Elite Crew", "Basketball Socks", "7 Colours", "US$16"),
            ShopProductData(R.drawable.img_home_airforce, true, "Nike Air Force 1", "Women's Shoes", "5 Colours", "US$115"),
            ShopProductData(R.drawable.img_shop_jodan1, true, "Jordan Essentials", "Men's Shoes", "2 Colours", "US$115")
        )
        dataStoreManager.saveProductList(dummyList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}