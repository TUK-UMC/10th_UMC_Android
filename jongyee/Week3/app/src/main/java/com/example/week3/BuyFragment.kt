package com.example.week3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week3.databinding.FragmentBuyBinding

class BuyFragment : Fragment() {
    private var _binding: FragmentBuyBinding? = null
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
        _binding = FragmentBuyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val products = listOf(
            Product(3, "Nike Everyday Plus Cushioned", "US$10", "Training Ankle Socks", R.drawable.trainingsocks, category = "Training Ankle Socks (6 Pairs)", colorsCount = "5 Colours"),
            Product(4, "Nike Elite Crew", "US$16", "Performance Socks", R.drawable.jordan, category = "Basketball Socks", colorsCount = "7 Colours"),
            Product(5, "Nike Air Force 1 '07", "US$115", "The legend lives on", R.drawable.airforce, subTitle = "BestSeller", category = "Women's Shoes", colorsCount = "5 Colours"),
            Product(6, "Jordan Series .05", "US$115", "Men's Shoes", R.drawable.crewsocks, subTitle = "BestSeller", category = "Men's Shoes", colorsCount = "2 Colours")
        )

        val productAdapter = ProductAdapter(products, isGrid = true) { product ->
            navigator?.onNavigateToDetail(product)
        }

        binding.buyRvProducts.adapter = productAdapter
        binding.buyRvProducts.layoutManager = GridLayoutManager(context, 2)
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