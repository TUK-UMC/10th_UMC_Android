package com.example.week3.shop

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.week3.R
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


        // 초기 하트 상태 설정
        updateFavoriteIcon(product.isFavorite)

        // 하트 아이콘 클릭 리스너
        binding.itemWishlist.setOnClickListener {
            // 1. 데이터 상태 반전 (true -> false, false -> true)
            product.isFavorite = !product.isFavorite

            // 2. 바뀐 상태에 따라 아이콘 모양/색상 변경
            updateFavoriteIcon(product.isFavorite)
        }


    }
    private fun updateFavoriteIcon(isFavorite: Boolean) {
        if (isFavorite) {
            // 즐겨찾기 상태일 때 (빨간색 하트 아이콘 사용)
            binding.itemWishlist.setImageResource(R.drawable.ic_nav_wishlist) // 빨간 하트 파일명
            // 만약 이미지는 하나고 색상만 바꾸고 싶다면?
            // binding.itemWishlist.setColorFilter(Color.RED)
        } else {
            // 해제 상태일 때 (빈 하트 아이콘 사용)
            binding.itemWishlist.setImageResource(R.drawable.ic_nav_wishlist) // 기본 하트
            // binding.itemWishlist.clearColorFilter()
        }
    }
}

