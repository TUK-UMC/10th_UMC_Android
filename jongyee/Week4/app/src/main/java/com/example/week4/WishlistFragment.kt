package com.example.week4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week4.databinding.FragmentWishlistBinding
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val productAdapter by lazy {
        ProductAdapter(emptyList(), isGrid = true,
            onItemClick = { product -> sharedViewModel.requestNavigateToDetail(product) },
            onHeartClick = { product -> sharedViewModel.toggleFavorite(product) } // 여기서 누르면 삭제됨
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.wishlistRvProducts.adapter = productAdapter
        binding.wishlistRvProducts.layoutManager = GridLayoutManager(context, 2)

        lifecycleScope.launch {
            sharedViewModel.buyProducts.collect { products ->
                val wishItems = products.filter { it.isFavorite }
                productAdapter.updateData(wishItems)
            }
        }
    }
    // (onDestroyView 생략)
}