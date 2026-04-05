package com.example.week3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week3.R
import com.example.week3.databinding.FragmentHomeBinding
import com.example.week3.home.HomeProductData

// 보여지는 화면 Main
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        // 1. 데이터 리스트 생성
        val homeProductList = arrayListOf(
            HomeProductData(R.drawable.home_airforce, "Nike Air Force 1'07", "US $115"),
            HomeProductData(R.drawable.home_jordan, "Air Jordan XXXVI", "US $185")
        )

        // 2. 어댑터 초기화 (클릭 시 동작 포함)
        val productAdapter = HomeProductAdapter(homeProductList) { product ->
            // 클릭했을 때 토스트 메시지 띄우기
            Toast.makeText(requireContext(), "${product.name} 클릭됨!", Toast.LENGTH_SHORT).show()
        }

        // 3. 리사이클러뷰 설정
        binding.homeRecyclerview.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}