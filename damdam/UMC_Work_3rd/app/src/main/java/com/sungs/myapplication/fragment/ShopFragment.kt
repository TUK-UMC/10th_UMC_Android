package com.sungs.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sungs.myapplication.R
import com.sungs.myapplication.adapter.ShopProductAdapter
import com.sungs.myapplication.data.ProductData
import com.sungs.myapplication.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = listOf(
            ProductData("Nike Everyday Plus Cushioned", "Training Crew Socks", "US$22", R.drawable.img_socks_everyday),
            ProductData("Nike Elite Crew", "Basketball Socks", "US$18", R.drawable.img_socks_elite),
            ProductData("Air Jordan 1 Mid", "Shoes", "US$125", R.drawable.img_jordan_1_mid),
            ProductData("Nike Air Force 1 '07", "Shoes", "US$115", R.drawable.img_air_force_1),
            ProductData("Nike Dunk Low", "Shoes", "US$115", R.drawable.img_dunk_low)
        )

        binding.rvShopProducts.adapter = ShopProductAdapter(productList)
        binding.rvShopProducts.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}