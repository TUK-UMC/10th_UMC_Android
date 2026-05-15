package com.example.week4.wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week4.databinding.ItemWishlistProductBinding
import com.example.week4.shop.ShopProductData // Shop 데이터 클래스 임포트

class WishlistAdapter(private var productList: ArrayList<ShopProductData>) :
RecyclerView.Adapter<WishlistViewHolder>() {

    // 데이터 갱신을 위한 함수 추가
    fun updateData(newList: ArrayList<ShopProductData>) {
        this.productList = newList
        notifyDataSetChanged() // 화면 다시 그리기
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val binding = ItemWishlistProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WishlistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}