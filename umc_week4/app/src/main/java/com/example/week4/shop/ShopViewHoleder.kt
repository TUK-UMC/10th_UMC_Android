package com.example.week4.shop

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.week4.R
import com.example.week4.databinding.ItemShopProductBinding

class ShopViewHolder(private val binding: ItemShopProductBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ShopProductData, onFavoriteClick: (ShopProductData) -> Unit) {
        binding.itemShopIv.setImageResource(product.image)
        binding.itemShopBestsellerTv.visibility = if (product.isBestSeller) View.VISIBLE else View.GONE
        binding.itemShopNameTv.text = product.name
        binding.itemShopPriceTv.text = product.price
        binding.itemShopCategoryTv.text = product.category
        binding.itemShopColorsTv.text = product.colors

        // 하트 상태 초기화
        updateFavoriteIcon(product.isFavorite)

        binding.itemWishlist.setOnClickListener {
            product.isFavorite = !product.isFavorite // 상태 반전
            updateFavoriteIcon(product.isFavorite)   // UI 변경
            onFavoriteClick(product)                 // 뷰모델로 전달하기 위해 콜백 호출
        }
    }

    private fun updateFavoriteIcon(isFavorite: Boolean) {
        if (isFavorite) {
            binding.itemWishlist.setImageResource(R.drawable.ic_nav_wishlistred) // 빨간 하트
        } else {
            binding.itemWishlist.setImageResource(R.drawable.ic_nav_wishlist) // 빈 하트
        }
    }
}

