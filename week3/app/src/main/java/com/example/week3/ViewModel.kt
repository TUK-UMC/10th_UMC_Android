package com.example.week3.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    // 찜한 아이템들만 담는 리스트
    private val _wishlist = MutableLiveData<MutableList<ShopProductData>>(mutableListOf())
    val wishlist: LiveData<MutableList<ShopProductData>> get() = _wishlist

    fun toggleWishlist(product: ShopProductData) {
        val currentList = _wishlist.value ?: mutableListOf()

        if (product.isFavorite) {
            // 리스트에 없으면 추가
            if (!currentList.any { it.name == product.name }) {
                currentList.add(product)
            }
        } else {
            // 리스트에서 제거
            currentList.removeAll { it.name == product.name }
        }
        _wishlist.value = currentList
    }
}