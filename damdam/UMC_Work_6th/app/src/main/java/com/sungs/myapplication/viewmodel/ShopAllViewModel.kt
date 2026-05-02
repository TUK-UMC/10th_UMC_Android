package com.sungs.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sungs.myapplication.R
import com.sungs.myapplication.data.ProductData
import com.sungs.myapplication.domain.repository.ProductLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopAllViewModel @Inject constructor(
    private val repository: ProductLocalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ShopAllUiState())
    val uiState: StateFlow<ShopAllUiState> = _uiState.asStateFlow()

    private val defaultProducts = listOf(
        ProductData("Nike Everyday Plus Cushioned", "Training Crew Socks", "US\$22", R.drawable.img_socks_everyday),
        ProductData("Nike Elite Crew", "Basketball Socks", "US\$18", R.drawable.img_socks_elite),
        ProductData("Air Jordan 1 Mid", "Shoes", "US\$125", R.drawable.img_jordan_1_mid),
        ProductData("Nike Air Force 1 '07", "Shoes", "US\$115", R.drawable.img_air_force_1),
        ProductData("Nike Dunk Low", "Shoes", "US\$115", R.drawable.img_dunk_low)
    )

    init {
        loadShopProducts()
    }

    private fun loadShopProducts() {
        viewModelScope.launch {
            val existing = repository.getShopProducts().first()
            if (existing.isEmpty()) {
                repository.saveShopProducts(defaultProducts)
            }

            // 상품 + 즐겨찾기를 합쳐 isFavorite을 채워준 리스트를 만든다
            repository.getShopProducts()
                .combine(repository.getFavorites()) { products, favs ->
                    products.map { it.copy(isFavorite = favs.contains(it.name)) }
                }
                .collect { merged ->
                    _uiState.update { it.copy(products = merged) }
                }
        }
    }

    fun toggleFavorite(productName: String) {
        viewModelScope.launch {
            repository.toggleFavorite(productName)
        }
    }
}

data class ShopAllUiState(
    val products: List<ProductData> = emptyList()
)