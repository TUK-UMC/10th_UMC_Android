package com.sungs.myapplication.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination {

    @Serializable
    data object Home : AppDestination

    @Serializable
    data object Shop : AppDestination

    @Serializable
    data object Wishlist : AppDestination

    @Serializable
    data object Bag : AppDestination

    @Serializable
    data object Profile : AppDestination

    @Serializable
    data object ProfileEdit : AppDestination
}