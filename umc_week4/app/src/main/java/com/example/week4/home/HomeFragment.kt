package com.example.week4.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week3.home.HomeDataStoreManager
import com.example.week4.R
import com.example.week4.databinding.FragmentHomeBinding
import com.example.week4.home.HomeProductData
import kotlinx.coroutines.launch

// 보여지는 화면 Main
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // 1. 매니저와 어댑터 선언
    private lateinit var homeDataStoreManager: HomeDataStoreManager
    private lateinit var homeProductAdapter: HomeProductAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. 초기화
        homeDataStoreManager = HomeDataStoreManager(requireContext())
        initRecyclerView()
        observeHomeData() // 비동기 데이터 관찰
    }

    private fun initRecyclerView() {
        // 처음에 빈 리스트로 어댑터 연결 mutable 방식
        homeProductAdapter = HomeProductAdapter(mutableListOf()) { product ->
            Toast.makeText(requireContext(), "${product.name} 클릭됨!", Toast.LENGTH_SHORT).show()
        }

        binding.homeRecyclerview.apply {
            adapter = homeProductAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun observeHomeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeDataStoreManager.getProductList().collect { products ->
                if (products.isEmpty()) {
                    // 3. 데이터가 없으면 최초 더미 데이터 저장
                    saveInitialHomeData()
                } else {
                    // 4. 데이터가 있으면 어댑터 갱신
                    homeProductAdapter.updateData(products)
                }
            }
        }
    }

    private suspend fun saveInitialHomeData() {
        val dummyList = listOf(
            HomeProductData(R.drawable.img_home_airforce, "Nike Air Force 1'07", "US $115"),
            HomeProductData(R.drawable.img_home_jordan, "Air Jordan XXXVI", "US $185")
        )
        homeDataStoreManager.saveProductList(dummyList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}