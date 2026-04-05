package com.example.week4.wishlist

import android.view.View
import android.graphics.Color // 색상 설정을 위해 필요
import androidx.recyclerview.widget.RecyclerView
import com.example.week4.R
import com.example.week4.databinding.ItemWishlistProductBinding
import com.example.week4.shop.ShopProductData

class WishlistViewHolder(private val binding: ItemWishlistProductBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ShopProductData) {
        // 1. 이미지 설정
        binding.itemWishlistIv.setImageResource(product.image)

        // 2. 텍스트 데이터 설정
        binding.itemWishlistNameTv.text = product.name
        binding.itemWishlistPriceTv.text = product.price

        // 카테고리 (비어있으면 숨기기)
        binding.itemWishlistCategoryTv.text = product.category
        binding.itemWishlistCategoryTv.visibility =
            if (product.category.isNullOrBlank()) View.GONE else View.VISIBLE

        // 컬러 정보 (비어있으면 숨기기)
        binding.itemWishlistColorsTv.text = product.colors
        binding.itemWishlistColorsTv.visibility =
            if (product.colors.isNullOrBlank()) View.GONE else View.VISIBLE

    }
}