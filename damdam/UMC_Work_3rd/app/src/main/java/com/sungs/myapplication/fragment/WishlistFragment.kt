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
import com.sungs.myapplication.databinding.FragmentWishlistBinding

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

        val wishList = listOf(
            ProductData("Air Jordan 1 Mid", "Shoes", "US$125", R.drawable.img_jordan_1_mid),
            ProductData("Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)\n5 Colours", "US$10", R.drawable.img_socks_everyday)
        )

        binding.rvWishlist.adapter = ProductAdapter(wishList)
        binding.rvWishlist.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}