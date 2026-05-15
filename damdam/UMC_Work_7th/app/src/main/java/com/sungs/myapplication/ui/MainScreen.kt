package com.sungs.myapplication.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sungs.myapplication.navigation.AppDestination
import com.sungs.myapplication.ui.component.AppBottomBar
import com.sungs.myapplication.ui.component.BottomTab
import com.sungs.myapplication.ui.screen.BagScreen
import com.sungs.myapplication.ui.screen.HomeScreen
import com.sungs.myapplication.ui.screen.ProfileEditScreen
import com.sungs.myapplication.ui.screen.ProfileScreen
import com.sungs.myapplication.ui.screen.ShopScreen
import com.sungs.myapplication.ui.screen.WishlistScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    val selectedTab = BottomTab.entries.find { tab ->
        currentDestination?.hasRouteSafely(tab.destination) == true
    }

    Scaffold(
        bottomBar = {
            // ProfileEdit 같은 상세 화면에서는 BottomBar 숨김
            if (selectedTab != null) {
                AppBottomBar(
                    selectedTab = selectedTab,
                    onTabClick = { tab ->
                        navController.navigate(tab.destination) {
                            popUpTo(AppDestination.Home) {
                                saveState = true
                                inclusive = false
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestination.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<AppDestination.Home> {
                HomeScreen()
            }
            composable<AppDestination.Shop> {
                ShopScreen()
            }
            composable<AppDestination.Wishlist> {
                WishlistScreen()
            }
            composable<AppDestination.Bag> {
                BagScreen(
                    onOrderClick = {
                        navController.navigate(AppDestination.Shop) {
                            popUpTo(AppDestination.Home) {
                                saveState = true
                                inclusive = false
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable<AppDestination.Profile> {
                ProfileScreen(
                    onEditClick = {
                        navController.navigate(AppDestination.ProfileEdit)
                    }
                )
            }
            composable<AppDestination.ProfileEdit> {
                ProfileEditScreen()
            }
        }
    }
}


private fun NavDestination.hasRouteSafely(route: Any): Boolean {
    return this.hasRoute(route::class)
}