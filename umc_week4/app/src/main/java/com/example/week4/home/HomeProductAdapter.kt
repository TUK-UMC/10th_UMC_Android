package com.example.week4.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week4.databinding.ItemHomeProductBinding
import com.example.week4.home.HomeProductData

//데이터 리스트를 관리하고, 뷰홀더를 생성해서 연결
class HomeProductAdapter(
    private val productList: MutableList<HomeProductData>,
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

    // 데이터를 갱신하는 함수 추가
    fun updateData(newList: List<HomeProductData>) {
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }
}