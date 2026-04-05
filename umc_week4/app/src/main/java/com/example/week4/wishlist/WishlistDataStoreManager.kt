package com.example.week4.wishlist

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.week4.shop.ShopProductData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 확장 프로퍼티로 DataStore 선언
private val Context.dataStore by preferencesDataStore(name = "home_preferences")

class WishlistDataStoreManager(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "wishlist_prefs")
    private val gson = Gson()
    private val WISH_KEY = stringPreferencesKey("wishlist_items")

    // 위시리스트 저장
    suspend fun saveWishlist(products: List<ShopProductData>) {
        val jsonString = gson.toJson(products)
        context.dataStore.edit { it[WISH_KEY] = jsonString }
    }

    // 위시리스트 불러오기 (최초 1회용 또는 Flow)
    fun getWishlist(): Flow<List<ShopProductData>> = context.dataStore.data.map { prefs ->
        val jsonString = prefs[WISH_KEY] ?: ""
        if (jsonString.isEmpty()) emptyList()
        else {
            val type = object : TypeToken<List<ShopProductData>>() {}.type
            gson.fromJson(jsonString, type)
        }
    }
}