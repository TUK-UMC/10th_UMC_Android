package com.example.week3.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week3.databinding.ItemShopProductBinding

class ShopProductAdapter(
    private val productList: List<ShopProductData>,
    private val onFavoriteClick: (ShopProductData) -> Unit, // 추가
    private val onItemClick: (ShopProductData) -> Unit
) : RecyclerView.Adapter<ShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val binding = ItemShopProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val data = productList[position]
        // holder.bind에 콜백 전달
        holder.bind(data, onFavoriteClick)

        holder.itemView.setOnClickListener { onItemClick(data) }
    }

    override fun getItemCount(): Int = productList.size
}