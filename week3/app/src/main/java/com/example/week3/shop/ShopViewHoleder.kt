package com.example.week3.shop

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.week3.databinding.ItemShopProductBinding

class ShopViewHolder(private val binding: ItemShopProductBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ShopProductData) {
        // 이미지 설정
        binding.itemShopIv.setImageResource(product.image)

        // 베스트셀러 표시 여부
        binding.itemShopBestsellerTv.visibility =
            if (product.isBestSeller) View.VISIBLE else View.GONE

        // 텍스트 데이터 설정
        binding.itemShopNameTv.text = product.name
        binding.itemShopPriceTv.text = product.price

        // 카테고리 (비어있으면 숨기기)
        binding.itemShopCategoryTv.text = product.category
        binding.itemShopCategoryTv.visibility =
            if (product.category.isNullOrBlank()) View.GONE else View.VISIBLE

        // 컬러 정보 (비어있으면 숨기기)
        binding.itemShopColorsTv.text = product.colors
        binding.itemShopColorsTv.visibility =
            if (product.colors.isNullOrBlank()) View.GONE else View.VISIBLE
    }
}