package com.example.week3.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week3.databinding.ItemShopProductBinding

class ShopProductAdapter(
    private val productList: List<ShopProductData>, // Shop 전용 데이터로 변경
    private val onItemClick: (ShopProductData) -> Unit
) : RecyclerView.Adapter<ShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        // Shop 전용 아이템 레이아웃 바인딩 사용
        val binding = ItemShopProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val data = productList[position]
        holder.bind(data) // 데이터를 뷰홀더에 전달

        // 클릭 이벤트 연결
        holder.itemView.setOnClickListener {
            onItemClick(data)
        }
    }

    override fun getItemCount(): Int = productList.size
}