package com.sungs.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sungs.myapplication.data.ProductData
import com.sungs.myapplication.domain.repository.ProductLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val repository: ProductLocalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WishlistUiState())
    val uiState: StateFlow<WishlistUiState> = _uiState.asStateFlow()

    init {
        loadWishlist()
    }

    private fun loadWishlist() {
        viewModelScope.launch {
            repository.getShopProducts()
                .combine(repository.getFavorites()) { products, favs ->
                    products.filter { favs.contains(it.name) }
                }
                .collect { wishProducts ->
                    _uiState.update { it.copy(products = wishProducts) }
                }
        }
    }
}

data class WishlistUiState(
    val products: List<ProductData> = emptyList()
)
