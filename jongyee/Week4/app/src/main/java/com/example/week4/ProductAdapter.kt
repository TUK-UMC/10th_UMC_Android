package com.example.week4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week4.databinding.ItemGridProductBinding
import com.example.week4.databinding.ItemHomeProductBinding

class ProductAdapter(
    private var productList: List<Product> = emptyList(), // 기본값을 빈 리스트로 설정
    private val isGrid: Boolean = false,
    private val onHeartClick: ((Product) -> Unit)? = null, // 🌟 하트 클릭 이벤트 매개변수 추가!
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    fun updateData(newList: List<Product>) {
        productList = newList
        notifyDataSetChanged() // 화면 새로고침
    }

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

            updateHeartIcon(product.isFavorite)
            binding.ivHeartGrid.setOnClickListener {
                onHeartClick?.invoke(product)
            }

            binding.root.setOnClickListener { onItemClick(product) }
        }

        private fun updateHeartIcon(isFavorite: Boolean) {
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