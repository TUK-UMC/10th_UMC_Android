package com.sungs.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungs.myapplication.R
import com.sungs.myapplication.data.ProductData
import com.sungs.myapplication.databinding.ItemShopProductBinding

class ShopProductAdapter(
    private var productList: List<ProductData>,
    private val onFavoriteClick: (ProductData) -> Unit
) : RecyclerView.Adapter<ShopProductAdapter.ShopProductViewHolder>() {

    private var favoriteNames: Set<String> = emptySet()

    fun updateFavorites(names: Set<String>) {
        favoriteNames = names
        notifyDataSetChanged()
    }

    fun updateProducts(products: List<ProductData>) {
        productList = products
        notifyDataSetChanged()
    }

    inner class ShopProductViewHolder(private val binding: ItemShopProductBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductData) {
            binding.ivProductImage.setImageResource(product.imageResId)
            binding.tvProductCategory.text = product.category
            binding.tvProductName.text = product.name
            binding.tvProductPrice.text = product.price

            val isFav = favoriteNames.contains(product.name)
            binding.ivHeart.setImageResource(
                if (isFav) R.drawable.ic_heart_filled
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
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}