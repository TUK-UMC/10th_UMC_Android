package com.example.week3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week3.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    private var navigator: NavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationListener) navigator = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wishItems = listOf(
            Product(2, "Air Jordan 1 Mid", "US$125", "Classic Style", R.drawable.trainingsocks),
            Product(3, "Nike Everyday Plus Cushioned", "US$10", "Training Ankle Socks", R.drawable.crewsocks, category = "Training Ankle Socks (6 Pairs)", colorsCount = "5 Colours")
        )

        val productAdapter = ProductAdapter(wishItems, isGrid = true) { product ->
            navigator?.onNavigateToDetail(product)
        }

        binding.wishlistRvProducts.adapter = productAdapter
        binding.wishlistRvProducts.layoutManager = GridLayoutManager(context, 2)
    }

    override fun onDetach() {
        super.onDetach()
        navigator = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}