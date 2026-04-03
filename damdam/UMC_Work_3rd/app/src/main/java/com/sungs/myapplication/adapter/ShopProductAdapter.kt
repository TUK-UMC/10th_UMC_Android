package com.sungs.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungs.myapplication.R
import com.sungs.myapplication.data.ProductData
import com.sungs.myapplication.databinding.ItemShopProductBinding

class ShopProductAdapter(private val productList: List<ProductData>)
    : RecyclerView.Adapter<ShopProductAdapter.ShopProductViewHolder>() {

    // 각 아이템의 하트 상태 저장
    private val favoriteStates = BooleanArray(productList.size) { false }

    inner class ShopProductViewHolder(private val binding: ItemShopProductBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductData, position: Int) {
            binding.ivProductImage.setImageResource(product.imageResId)
            binding.tvProductCategory.text = product.category
            binding.tvProductName.text = product.name
            binding.tvProductPrice.text = product.price

            // 하트 상태 반영
            binding.ivHeart.setImageResource(
                if (favoriteStates[position]) R.drawable.ic_heart_filled
                else R.drawable.ic_heart_outline
            )

            // 하트 클릭 토글
            binding.ivHeart.setOnClickListener {
                favoriteStates[position] = !favoriteStates[position]
                binding.ivHeart.setImageResource(
                    if (favoriteStates[position]) R.drawable.ic_heart_filled
                    else R.drawable.ic_heart_outline
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopProductViewHolder {
        val binding = ItemShopProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ShopProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopProductViewHolder, position: Int) {
        holder.bind(productList[position], position)
    }

    override fun getItemCount(): Int = productList.size
}