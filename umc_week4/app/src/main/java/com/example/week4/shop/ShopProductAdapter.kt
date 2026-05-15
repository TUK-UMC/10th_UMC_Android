package com.example.week4.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week4.databinding.ItemShopProductBinding

class ShopProductAdapter(
    private val productList: MutableList<ShopProductData>,
    private val onFavoriteClick: (ShopProductData) -> Unit,
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

    fun updateData(newList: List<ShopProductData>) {
        productList.clear()       // 이제 강제 변환 없이 바로 비울 수 있습니다.
        productList.addAll(newList) // 새로운 데이터 추가
        notifyDataSetChanged()    // 화면 새로고침
    }

    override fun getItemCount(): Int = productList.size
}