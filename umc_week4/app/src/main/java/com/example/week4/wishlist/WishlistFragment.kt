package com.example.week4.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels // 1. 반드시 이 import가 있어야 합니다.
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week4.databinding.FragmentWishlistBinding
import com.example.week4.shop.SharedViewModel

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    // 2. 라이브러리 제공 함수를 사용합니다.
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wishListAdapter = WishlistAdapter(arrayListOf())
        binding.wishlistRecyclerview.apply {
            adapter = wishListAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        // ViewModel이 DataStore에서 불러온 데이터를 자동으로 뿌려줌
        sharedViewModel.wishlist.observe(viewLifecycleOwner) { items ->
            wishListAdapter.updateData(ArrayList(items))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}