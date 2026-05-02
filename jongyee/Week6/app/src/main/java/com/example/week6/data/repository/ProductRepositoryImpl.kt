package com.example.week6.data.repository

import com.example.week6.Product
import com.example.week6.R
import com.example.week6.data.local.ProductLocalDataSource
import com.example.week6.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val localDataSource: ProductLocalDataSource
) : ProductRepository {

    private val defaultHomeProducts = listOf(
        Product(1, "Air Jordan XXXVI", "US$185", "Latest performance basketball shoe.", R.drawable.jordan),
        Product(2, "Nike Dunk Low", "US$110", "A hoop icon returns with classic colors.", R.drawable.airforce)
    )

    private val defaultBuyProducts = listOf(
        Product(3, "Nike Everyday Plus Cushioned", "US$10", "Training Ankle Socks", R.drawable.trainingsocks, category = "Training Ankle Socks (6 Pairs)", colorsCount = "5 Colours"),
        Product(4, "Nike Elite Crew", "US$16", "Performance Socks", R.drawable.jordan, category = "Basketball Socks", colorsCount = "7 Colours"),
        Product(5, "Nike Air Force 1 '07", "US$115", "The legend lives on", R.drawable.airforce, subTitle = "BestSeller", category = "Women's Shoes", colorsCount = "5 Colours"),
        Product(6, "Jordan Series .05", "US$115", "Men's Shoes", R.drawable.crewsocks, subTitle = "BestSeller", category = "Men's Shoes", colorsCount = "2 Colours")
    )

    override val homeProducts: Flow<List<Product>> =
        localDataSource.homeProductsFlow.map { it ?: defaultHomeProducts }

    override val buyProducts: Flow<List<Product>> =
        localDataSource.buyProductsFlow.map { it ?: defaultBuyProducts }

    override suspend fun saveHomeProducts(products: List<Product>) =
        localDataSource.saveHomeProducts(products)

    override suspend fun saveBuyProducts(products: List<Product>) =
        localDataSource.saveBuyProducts(products)
}
