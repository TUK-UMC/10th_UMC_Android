package com.sungs.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sungs.myapplication.R
import com.sungs.myapplication.adapter.ProductAdapter
import com.sungs.myapplication.data.ProductData
import com.sungs.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = listOf(
            ProductData("Air Jordan 1000R", "Shoes", "US$189", R.drawable.img_dunk_low),
            ProductData("Nike Air Force 1 '07", "Shoes", "US$115", R.drawable.img_air_force_1)
        )

        binding.rvHomeProducts.adapter = ProductAdapter(productList)
        binding.rvHomeProducts.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}