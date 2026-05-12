package com.example.week6.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.week6.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "nike_store")

@Singleton
class ProductLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val gson = Gson()
    private val HOME_KEY = stringPreferencesKey("home_products")
    private val BUY_KEY = stringPreferencesKey("buy_products")

    val homeProductsFlow: Flow<List<Product>?> = context.dataStore.data.map { prefs ->
        prefs[HOME_KEY]?.let { json ->
            gson.fromJson(json, object : TypeToken<List<Product>>() {}.type)
        }
    }

    val buyProductsFlow: Flow<List<Product>?> = context.dataStore.data.map { prefs ->
        prefs[BUY_KEY]?.let { json ->
            gson.fromJson(json, object : TypeToken<List<Product>>() {}.type)
        }
    }

    suspend fun saveHomeProducts(products: List<Product>) {
        context.dataStore.edit { it[HOME_KEY] = gson.toJson(products) }
    }

    suspend fun saveBuyProducts(products: List<Product>) {
        context.dataStore.edit { it[BUY_KEY] = gson.toJson(products) }
    }
}
