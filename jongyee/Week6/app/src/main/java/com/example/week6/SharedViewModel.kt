package com.example.week6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week6.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _homeProducts = MutableStateFlow<List<Product>>(emptyList())
    val homeProducts: StateFlow<List<Product>> get() = _homeProducts

    private val _buyProducts = MutableStateFlow<List<Product>>(emptyList())
    val buyProducts: StateFlow<List<Product>> get() = _buyProducts

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> get() = _selectedProduct

    private val _navigateToDetail = MutableSharedFlow<Unit>()
    val navigateToDetail = _navigateToDetail.asSharedFlow()

    private val _navigateToBuyTab = MutableSharedFlow<Unit>()
    val navigateToBuyTab = _navigateToBuyTab.asSharedFlow()

    init {
        viewModelScope.launch {
            productRepository.homeProducts.collect { _homeProducts.value = it }
        }
        viewModelScope.launch {
            productRepository.buyProducts.collect { _buyProducts.value = it }
        }
    }

    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            val newFavStatus = !product.isFavorite

            val updatedBuyList = _buyProducts.value.map {
                if (it.id == product.id) it.copy(isFavorite = newFavStatus) else it
            }
            _buyProducts.value = updatedBuyList
            productRepository.saveBuyProducts(updatedBuyList)

            val updatedHomeList = _homeProducts.value.map {
                if (it.id == product.id) it.copy(isFavorite = newFavStatus) else it
            }
            _homeProducts.value = updatedHomeList
            productRepository.saveHomeProducts(updatedHomeList)

            if (_selectedProduct.value?.id == product.id) {
                _selectedProduct.value = _selectedProduct.value?.copy(isFavorite = newFavStatus)
            }
        }
    }

    fun requestNavigateToDetail(product: Product) {
        _selectedProduct.value = product
        viewModelScope.launch { _navigateToDetail.emit(Unit) }
    }

    fun requestNavigateToBuyTab() {
        viewModelScope.launch { _navigateToBuyTab.emit(Unit) }
    }
}
