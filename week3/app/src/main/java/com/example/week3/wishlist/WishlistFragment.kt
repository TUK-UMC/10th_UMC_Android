package com.example.week3.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels // 1. 반드시 이 import가 있어야 합니다.
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week3.databinding.FragmentWishlistBinding
import com.example.week3.shop.SharedViewModel
import com.example.week3.shop.ShopProductData

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    // 2. 라이브러리 제공 함수를 사용합니다. (뒤에 직접 만든 함수는 삭제하세요!)
    private val sharedViewModel: SharedViewModel by activityViewModels()

    // [삭제됨] private fun activityViewModels()... 코드는 지워야 합니다.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 3. 어댑터 초기화
        val wishListAdapter = WishlistAdapter(arrayListOf())

        binding.wishlistRecyclerview.apply {
            adapter = wishListAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        // 4. ViewModel 관찰 (데이터 변경 시 어댑터 갱신)
        sharedViewModel.wishlist.observe(viewLifecycleOwner) { items ->
            // items(List)를 ArrayList로 변환하여 전달
            wishListAdapter.updateData(ArrayList(items))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}