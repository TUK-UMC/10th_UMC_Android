package com.sungs.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.sungs.myapplication.R
import com.sungs.myapplication.adapter.ProductAdapter
import com.sungs.myapplication.data.ProductData
import com.sungs.myapplication.data.ProductDataStore
import com.sungs.myapplication.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val defaultProducts = listOf(
        ProductData("Air Jordan 1000R", "Shoes", "US$189", R.drawable.img_dunk_low),
        ProductData("Nike Air Force 1 '07", "Shoes", "US$115", R.drawable.img_air_force_1)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHomeProducts.layoutManager = GridLayoutManager(requireContext(), 2)

        viewLifecycleOwner.lifecycleScope.launch {
            val existing = ProductDataStore.getHomeProducts(requireContext()).first()
            if (existing.isEmpty()) {
                ProductDataStore.saveHomeProducts(requireContext(), defaultProducts)
            }
            ProductDataStore.getHomeProducts(requireContext()).collect { products ->
                binding.rvHomeProducts.adapter = ProductAdapter(products)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}