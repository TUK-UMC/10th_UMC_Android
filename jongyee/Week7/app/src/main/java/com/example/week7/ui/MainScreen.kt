package com.example.week7.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.week7.navigation.AppDestination
import com.example.week7.ui.bag.BagScreen
import com.example.week7.ui.buy.BuyScreen
import com.example.week7.ui.home.HomeScreen
import com.example.week7.ui.profile.ProfileScreen
import com.example.week7.ui.theme.NikeBlack
import com.example.week7.ui.theme.NikeGray
import com.example.week7.ui.theme.NikeWhite
import com.example.week7.ui.wishlist.WishlistScreen

private data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val destination: AppDestination,
)

private val bottomNavItems = listOf(
    BottomNavItem("홈", Icons.Default.Home, AppDestination.Home),
    BottomNavItem("구매하기", Icons.Default.ShoppingCart, AppDestination.Buy),
    BottomNavItem("위시리스트", Icons.Default.Favorite, AppDestination.Wishlist),
    BottomNavItem("장바구니", Icons.Default.ShoppingBag, AppDestination.Bag),
    BottomNavItem("프로필", Icons.Default.Person, AppDestination.Profile),
)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = NikeWhite) {
                bottomNavItems.forEach { item ->
                    val selected = currentDestination?.hasRoute(item.destination::class) == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.destination) {
                                popUpTo(AppDestination.Home) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.label)
                        },
                        label = { Text(item.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = NikeBlack,
                            selectedTextColor = NikeBlack,
                            unselectedIconColor = NikeGray,
                            unselectedTextColor = NikeGray,
                            indicatorColor = NikeWhite,
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestination.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<AppDestination.Home> { HomeScreen() }
            composable<AppDestination.Buy> { BuyScreen() }
            composable<AppDestination.Wishlist> { WishlistScreen() }
            composable<AppDestination.Bag> {
                BagScreen(
                    onNavigateToBuy = {
                        navController.navigate(AppDestination.Buy) {
                            popUpTo(AppDestination.Home) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable<AppDestination.Profile> { ProfileScreen() }
        }
    }
}
