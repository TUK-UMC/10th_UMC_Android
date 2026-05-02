package com.sungs.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sungs.myapplication.R
import com.sungs.myapplication.data.ProductData
import com.sungs.myapplication.databinding.ItemShopProductBinding

class ShopProductAdapter(
    private val onFavoriteClick: (ProductData) -> Unit
) : ListAdapter<ProductData, ShopProductAdapter.ShopProductViewHolder>(ProductDiffCallback()) {

    inner class ShopProductViewHolder(private val binding: ItemShopProductBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductData) {
            binding.ivProductImage.setImageResource(product.imageResId)
            binding.tvProductCategory.text = product.category
            binding.tvProductName.text = product.name
            binding.tvProductPrice.text = product.price

            binding.ivHeart.setImageResource(
                if (product.isFavorite) R.drawable.ic_heart_filled
                else R.drawable.ic_heart_outline
            )

            binding.ivHeart.setOnClickListener {
                onFavoriteClick(product)
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
        holder.bind(getItem(position))
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<ProductData>() {
        override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem == newItem
        }
    }
}