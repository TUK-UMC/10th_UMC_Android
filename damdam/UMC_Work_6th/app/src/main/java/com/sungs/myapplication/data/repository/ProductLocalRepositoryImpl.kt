package com.sungs.myapplication.data.repository

import android.content.Context
import com.sungs.myapplication.data.ProductData
import com.sungs.myapplication.data.ProductDataStore
import com.sungs.myapplication.domain.repository.ProductLocalRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductLocalRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ProductLocalRepository {

    override fun getHomeProducts(): Flow<List<ProductData>> =
        ProductDataStore.getHomeProducts(context)

    override suspend fun saveHomeProducts(products: List<ProductData>) {
        ProductDataStore.saveHomeProducts(context, products)
    }

    override fun getShopProducts(): Flow<List<ProductData>> =
        ProductDataStore.getShopProducts(context)

    override suspend fun saveShopProducts(products: List<ProductData>) {
        ProductDataStore.saveShopProducts(context, products)
    }

    override fun getFavorites(): Flow<Set<String>> =
        ProductDataStore.getFavorites(context)

    override suspend fun toggleFavorite(productName: String) {
        ProductDataStore.toggleFavorite(context, productName)
    }
}