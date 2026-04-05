package com.example.week3.home

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.week4.home.HomeProductData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 확장 프로퍼티로 DataStore 선언
private val Context.dataStore by preferencesDataStore(name = "home_preferences")

class HomeDataStoreManager(private val context: Context) {
    //객체 -> json
    private val gson = Gson()
    private val PRODUCT_KEY = stringPreferencesKey("home_products")

    // 데이터 저장 (JSON 문자열로 변환)
    suspend fun saveProductList(products: List<HomeProductData>) {
        val jsonString = gson.toJson(products)
        context.dataStore.edit { it[PRODUCT_KEY] = jsonString }
    }

    // 데이터 불러오기 (Flow 반환)
    fun getProductList(): Flow<List<HomeProductData>> = context.dataStore.data.map { prefs ->
        val jsonString = prefs[PRODUCT_KEY] ?: ""
        if (jsonString.isEmpty()) emptyList()
        else {
            val type = object : TypeToken<List<HomeProductData>>() {}.type
            gson.fromJson(jsonString, type)
        }
    }
}