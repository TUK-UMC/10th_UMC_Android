package com.example.week3.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week3.databinding.ItemHomeProductBinding
import com.example.week3.home.HomeProductData

//데이터 리스트를 관리하고, 뷰홀더를 생성해서 연결
class HomeProductAdapter(
    private val productList: List<HomeProductData>,
    private val onItemClick: (HomeProductData) -> Unit
) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}