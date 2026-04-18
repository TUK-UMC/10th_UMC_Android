package com.sungs.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.sungs.myapplication.R
import com.sungs.myapplication.adapter.ShopProductAdapter
import com.sungs.myapplication.data.ProductData
import com.sungs.myapplication.data.ProductDataStore
import com.sungs.myapplication.databinding.FragmentShopAllBinding
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ShopAllFragment : Fragment() {

    private var _binding: FragmentShopAllBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentShopAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ShopProductAdapter { product ->
            viewLifecycleOwner.lifecycleScope.launch {
                ProductDataStore.toggleFavorite(requireContext(), product.name)
            }
        }

        binding.rvShopAll.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvShopAll.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            val existing = ProductDataStore.getShopProducts(requireContext()).first()
            if (existing.isEmpty()) {
                ProductDataStore.saveShopProducts(requireContext(), defaultProducts)
            }

            ProductDataStore.getShopProducts(requireContext())
                .combine(ProductDataStore.getFavorites(requireContext())) { products, favs ->
                    products.map { it.copy(isFavorite = favs.contains(it.name)) }
                }
                .collect { updatedProducts ->
                    adapter.submitList(updatedProducts)
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}