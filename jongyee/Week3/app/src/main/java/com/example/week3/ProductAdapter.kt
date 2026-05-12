package com.example.week3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week3.databinding.ItemGridProductBinding
import com.example.week3.databinding.ItemHomeProductBinding

class ProductAdapter(
    private var productList: List<Product>,
    private val isGrid: Boolean = false,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class HomeViewHolder(private val binding: ItemHomeProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.ivProduct.setImageResource(product.imageRes)
            binding.tvName.text = product.name
            binding.tvPrice.text = product.price
            binding.root.setOnClickListener { onItemClick(product) }
        }
    }

    inner class GridViewHolder(private val binding: ItemGridProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.ivProductGrid.setImageResource(product.imageRes)
            binding.tvNameGrid.text = product.name
            binding.tvPriceGrid.text = product.price
            binding.tvCategoryGrid.text = product.category
            binding.tvColorsGrid.text = product.colorsCount

            if (product.subTitle.isEmpty()) {
                binding.tvSubtitleGrid.visibility = View.GONE
            } else {
                binding.tvSubtitleGrid.visibility = View.VISIBLE
                binding.tvSubtitleGrid.text = product.subTitle
            }

            // 하트 아이콘 업데이트 함수 호출
            updateHeartIcon(product.isFavorite)

            // 하트 아이콘 클릭 시 상태 변경 (채워짐 <-> 꺼짐)
            binding.ivHeartGrid.setOnClickListener {
                product.isFavorite = !product.isFavorite
                updateHeartIcon(product.isFavorite)
            }

            binding.root.setOnClickListener { onItemClick(product) }
        }

        private fun updateHeartIcon(isFavorite: Boolean) {
            // 별 아이콘 대신 하트 아이콘 리소스 적용 (ic_heart_filled, ic_heart_empty)
            val iconRes = if (isFavorite) R.drawable.ic_heart_filled else R.drawable.ic_heart_empty
            binding.ivHeartGrid.setImageResource(iconRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (isGrid) {
            GridViewHolder(ItemGridProductBinding.inflate(layoutInflater, parent, false))
        } else {
            HomeViewHolder(ItemHomeProductBinding.inflate(layoutInflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = productList[position]
        if (holder is HomeViewHolder) holder.bind(product)
        else if (holder is GridViewHolder) holder.bind(product)
    }

    override fun getItemCount(): Int = productList.size
}