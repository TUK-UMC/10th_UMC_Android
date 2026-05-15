package com.sungs.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sungs.myapplication.R
import com.sungs.myapplication.data.model.ProductData
import com.sungs.myapplication.domain.repository.ProductLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductLocalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    private val defaultProducts = listOf(
        ProductData("Air Jordan 1000R", "Shoes", "US\$189", R.drawable.img_dunk_low),
        ProductData("Nike Air Force 1 '07", "Shoes", "US\$115", R.drawable.img_air_force_1)
    )

    init {
        loadHomeProducts()
    }

    private fun loadHomeProducts() {
        viewModelScope.launch {
            // 기존 데이터가 비었으면 default 저장 → 이후 collect로 자동 반영
            val existing = repository.getHomeProducts().first()
            if (existing.isEmpty()) {
                repository.saveHomeProducts(defaultProducts)
            }

            repository.getHomeProducts().collect { products ->
                _uiState.update { it.copy(products = products) }
            }
        }
    }
}

data class HomeUiState(
    val products: List<ProductData> = emptyList()
)
