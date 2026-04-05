package com.example.week4.shop

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.week4.wishlist.WishlistDataStoreManager
import kotlinx.coroutines.launch

// AndroidViewModel을 상속받아야 DataStore에 필요한 'context'를 사용할 수 있습니다.
class `SharedViewModel.kt`(application: Application) : AndroidViewModel(application) {

    // 위시리스트 전용 데이터스토어 매니저 초기화
    private val wishlistDataStore = WishlistDataStoreManager(application)

    // 외부(Fragment)에서 관찰할 LiveData
    private val _wishlist = MutableLiveData<List<ShopProductData>>(emptyList())
    val wishlist: LiveData<List<ShopProductData>> get() = _wishlist

    init {
        // [중요] 앱이 켜질 때 금고(DataStore)에서 저장된 위시리스트를 꺼내옵니다.
        viewModelScope.launch {
            wishlistDataStore.getWishlist().collect { savedItems ->
                _wishlist.value = savedItems
            }
        }
    }

    // 하트를 눌렀을 때 실행되는 함수
    fun toggleWishlist(product: ShopProductData) {
        val currentList = _wishlist.value?.toMutableList() ?: mutableListOf()

        // 하트가 켜진 경우 리스트에 추가, 꺼진 경우 제거
        if (product.isFavorite) {
            // 중복 추가 방지 (이름 기준)
            if (!currentList.any { it.name == product.name }) {
                currentList.add(product)
            }
        } else {
            currentList.removeAll { it.name == product.name }
        }

        // 1. 메모리상의 LiveData 업데이트 (즉시 화면 반영)
        _wishlist.value = currentList

        // 2. DataStore에 영구 저장 (앱 꺼져도 유지됨)
        viewModelScope.launch {
            wishlistDataStore.saveWishlist(currentList)
        }
    }
}