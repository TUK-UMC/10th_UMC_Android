package com.example.week4.fragment

import androidx.recyclerview.widget.RecyclerView
import com.example.week4.databinding.ItemHomeProductBinding // 아이템 레이아웃 바인딩
import com.example.week4.home.HomeProductData

// XML레이아웃을 UI에 맞게 제공
class HomeViewHolder(
    private val binding: ItemHomeProductBinding,
    private val onItemClick: (HomeProductData) -> Unit // 클릭 이벤트를 위한 람다
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: HomeProductData) {
        binding.ivHomeProduct.setImageResource(data.image)
        binding.tvHomeProductName.text = data.name
        binding.tvHomeProductPrice.text = data.price

        // 아이템 전체 클릭 리스너 설정
        binding.root.setOnClickListener {
            onItemClick(data)
        }
    }
}