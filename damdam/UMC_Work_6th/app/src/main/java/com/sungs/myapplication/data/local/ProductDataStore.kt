package com.sungs.myapplication.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sungs.myapplication.data.model.ProductData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "product_prefs")

@Singleton
class ProductDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val gson = Gson()

    // 키 정의
    private companion object {
        val HOME_PRODUCTS_KEY = stringPreferencesKey("home_products")
        val SHOP_PRODUCTS_KEY = stringPreferencesKey("shop_products")
        val FAVORITE_NAMES_KEY = stringPreferencesKey("favorite_names")
    }

    // ── 상품 리스트 저장/로드 ──

    suspend fun saveHomeProducts(products: List<ProductData>) {
        context.dataStore.edit { prefs ->
            prefs[HOME_PRODUCTS_KEY] = gson.toJson(products)
        }
    }

    fun getHomeProducts(): Flow<List<ProductData>> {
        return context.dataStore.data.map { prefs ->
            val json = prefs[HOME_PRODUCTS_KEY]
            if (json != null) {
                gson.fromJson(json, object : TypeToken<List<ProductData>>() {}.type)
            } else emptyList()
        }
    }

    suspend fun saveShopProducts(products: List<ProductData>) {
        context.dataStore.edit { prefs ->
            prefs[SHOP_PRODUCTS_KEY] = gson.toJson(products)
        }
    }

    fun getShopProducts(): Flow<List<ProductData>> {
        return context.dataStore.data.map { prefs ->
            val json = prefs[SHOP_PRODUCTS_KEY]
            if (json != null) {
                gson.fromJson(json, object : TypeToken<List<ProductData>>() {}.type)
            } else emptyList()
        }
    }

    // ── 즐겨찾기(하트) 관리 ──

    suspend fun saveFavorites(favoriteNames: Set<String>) {
        context.dataStore.edit { prefs ->
            prefs[FAVORITE_NAMES_KEY] = gson.toJson(favoriteNames.toList())
        }
    }

    fun getFavorites(): Flow<Set<String>> {
        return context.dataStore.data.map { prefs ->
            val json = prefs[FAVORITE_NAMES_KEY]
            if (json != null) {
                val list: List<String> = gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
                list.toSet()
            } else emptySet()
        }
    }

    suspend fun toggleFavorite(productName: String) {
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