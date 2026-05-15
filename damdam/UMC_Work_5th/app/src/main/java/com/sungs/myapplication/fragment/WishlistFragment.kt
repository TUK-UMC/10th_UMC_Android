package com.sungs.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.sungs.myapplication.adapter.ProductAdapter
import com.sungs.myapplication.data.ProductDataStore
import com.sungs.myapplication.databinding.FragmentWishlistBinding
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {

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

        binding.rvWishlist.layoutManager = GridLayoutManager(requireContext(), 2)

        viewLifecycleOwner.lifecycleScope.launch {
            // shop 상품 + 즐겨찾기 목록을 combine해서 필터
            ProductDataStore.getShopProducts(requireContext())
                .combine(ProductDataStore.getFavorites(requireContext())) { products, favs ->
                    products.filter { favs.contains(it.name) }
                }
                .collect { wishProducts ->
                    binding.rvWishlist.adapter = ProductAdapter(wishProducts)
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}