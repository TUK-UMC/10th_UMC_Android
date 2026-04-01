package com.example.week3.wishlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.week3.R
import com.example.week3.databinding.ItemWishlistProductBinding
import com.example.week3.shop.ShopProductData // Shop 데이터 클래스 임포트

class WishlistViewHolder(private val binding: ItemWishlistProductBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ShopProductData) {
        // 상품 이미지 설정
        binding.itemWishlistIv.setImageResource(product.image)

        // 베스트셀러 텍스트 표시 여부 (추가됨)
        // 만약 레이아웃에 베스트셀러 TV가 있다면 아래 코드를 활성화하세요.
        // binding.itemWishlistBestsellerTv.visibility =
        //    if (product.isBestSeller) View.VISIBLE else View.GONE

        // 상품명 설정
        binding.itemWishlistNameTv.text = product.name

        // 카테고리 설정 (비어있으면 숨기기)
        binding.itemWishlistCategoryTv.text = product.category
        binding.itemWishlistCategoryTv.visibility =
            if (product.category.isNullOrBlank()) View.GONE else View.VISIBLE

        // 컬러 정보 설정 (비어있으면 숨기기)
        binding.itemWishlistColorsTv.text = product.colors
        binding.itemWishlistColorsTv.visibility =
            if (product.colors.isNullOrBlank()) View.GONE else View.VISIBLE

        // 가격 설정
        binding.itemWishlistPriceTv.text = product.price

        binding.itemWishlistIv.setImageResource(R.drawable.ic_nav_wishlistred)
    }
}