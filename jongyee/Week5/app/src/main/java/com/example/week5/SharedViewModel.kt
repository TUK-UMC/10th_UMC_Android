package com.example.week5

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

private val Application.dataStore by preferencesDataStore(name = "nike_store")

class SharedViewModel(application: Application) : AndroidViewModel(application) {
    private val dataStore = application.dataStore
    private val gson = Gson()

    private val HOME_KEY = stringPreferencesKey("home_products")
    private val BUY_KEY = stringPreferencesKey("buy_products")

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
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            val prefs = dataStore.data.first()
            val type = object : TypeToken<List<Product>>() {}.type

            val homeJson = prefs[HOME_KEY]
            if (homeJson != null) {
                _homeProducts.value = gson.fromJson(homeJson, type)
            } else {
                val dummyHome = listOf(
                    Product(1, "Air Jordan XXXVI", "US$185", "Latest performance basketball shoe.", R.drawable.jordan),
                    Product(2, "Nike Dunk Low", "US$110", "A hoop icon returns with classic colors.", R.drawable.airforce)
                )
                saveToDataStore(HOME_KEY, dummyHome)
                _homeProducts.value = dummyHome
            }

            val buyJson = prefs[BUY_KEY]
            if (buyJson != null) {
                _buyProducts.value = gson.fromJson(buyJson, type)
            } else {
                val dummyBuy = listOf(
                    Product(3, "Nike Everyday Plus Cushioned", "US$10", "Training Ankle Socks", R.drawable.trainingsocks, category = "Training Ankle Socks (6 Pairs)", colorsCount = "5 Colours"),
                    Product(4, "Nike Elite Crew", "US$16", "Performance Socks", R.drawable.jordan, category = "Basketball Socks", colorsCount = "7 Colours"),
                    Product(5, "Nike Air Force 1 '07", "US$115", "The legend lives on", R.drawable.airforce, subTitle = "BestSeller", category = "Women's Shoes", colorsCount = "5 Colours"),
                    Product(6, "Jordan Series .05", "US$115", "Men's Shoes", R.drawable.crewsocks, subTitle = "BestSeller", category = "Men's Shoes", colorsCount = "2 Colours")
                )
                saveToDataStore(BUY_KEY, dummyBuy)
                _buyProducts.value = dummyBuy
            }
        }
    }

    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            val newFavStatus = !product.isFavorite

            val updatedBuyList = _buyProducts.value.map {
                if (it.id == product.id) it.copy(isFavorite = newFavStatus) else it
            }
            _buyProducts.value = updatedBuyList
            saveToDataStore(BUY_KEY, updatedBuyList)

            val updatedHomeList = _homeProducts.value.map {
                if (it.id == product.id) it.copy(isFavorite = newFavStatus) else it
            }
            _homeProducts.value = updatedHomeList
            saveToDataStore(HOME_KEY, updatedHomeList)

            if (_selectedProduct.value?.id == product.id) {
                _selectedProduct.value = _selectedProduct.value?.copy(isFavorite = newFavStatus)
            }
        }
    }

    private suspend fun saveToDataStore(key: androidx.datastore.preferences.core.Preferences.Key<String>, data: List<Product>) {
        dataStore.edit { prefs ->
            prefs[key] = gson.toJson(data)
        }
    }

    fun requestNavigateToDetail(product: Product) {
        _selectedProduct.value = product
        viewModelScope.launch {
            _navigateToDetail.emit(Unit)
        }
    }

    fun requestNavigateToBuyTab() {
        viewModelScope.launch {
            _navigateToBuyTab.emit(Unit)
        }
    }
}
