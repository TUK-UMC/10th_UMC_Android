package com.sungs.myapplication.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Context 확장 프로퍼티로 DataStore 싱글톤 생성
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "product_prefs")

object ProductDataStore {

    private val gson = Gson()

    // 키 정의
    private val HOME_PRODUCTS_KEY = stringPreferencesKey("home_products")
    private val SHOP_PRODUCTS_KEY = stringPreferencesKey("shop_products")
    private val FAVORITE_NAMES_KEY = stringPreferencesKey("favorite_names")

    // ── 상품 리스트 저장/로드 ──

    suspend fun saveHomeProducts(context: Context, products: List<ProductData>) {
        context.dataStore.edit { prefs ->
            prefs[HOME_PRODUCTS_KEY] = gson.toJson(products)
        }
    }

    fun getHomeProducts(context: Context): Flow<List<ProductData>> {
        return context.dataStore.data.map { prefs ->
            val json = prefs[HOME_PRODUCTS_KEY]
            if (json != null) {
                gson.fromJson(json, object : TypeToken<List<ProductData>>() {}.type)
            } else emptyList()
        }
    }

    suspend fun saveShopProducts(context: Context, products: List<ProductData>) {
        context.dataStore.edit { prefs ->
            prefs[SHOP_PRODUCTS_KEY] = gson.toJson(products)
        }
    }

    fun getShopProducts(context: Context): Flow<List<ProductData>> {
        return context.dataStore.data.map { prefs ->
            val json = prefs[SHOP_PRODUCTS_KEY]
            if (json != null) {
                gson.fromJson(json, object : TypeToken<List<ProductData>>() {}.type)
            } else emptyList()
        }
    }

    // ── 즐겨찾기(하트) 관리 ──
    // 즐겨찾기된 상품의 name을 Set으로 저장

    suspend fun saveFavorites(context: Context, favoriteNames: Set<String>) {
        context.dataStore.edit { prefs ->
            prefs[FAVORITE_NAMES_KEY] = gson.toJson(favoriteNames.toList())
        }
    }

    fun getFavorites(context: Context): Flow<Set<String>> {
        return context.dataStore.data.map { prefs ->
            val json = prefs[FAVORITE_NAMES_KEY]
            if (json != null) {
                val list: List<String> = gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
                list.toSet()
            } else emptySet()
        }
    }

    suspend fun toggleFavorite(context: Context, productName: String) {
        context.dataStore.edit { prefs ->
            val json = prefs[FAVORITE_NAMES_KEY]
            val current: MutableSet<String> = if (json != null) {
                val list: List<String> = gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
                list.toMutableSet()
            } else mutableSetOf()

            if (current.contains(productName)) current.remove(productName)
            else current.add(productName)

            prefs[FAVORITE_NAMES_KEY] = gson.toJson(current.toList())
        }
    }
}