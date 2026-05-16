package com.example.week7.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination {
    @Serializable data object Home : AppDestination
    @Serializable data object Buy : AppDestination
    @Serializable data object Wishlist : AppDestination
    @Serializable data object Bag : AppDestination
    @Serializable data object Profile : AppDestination
}
