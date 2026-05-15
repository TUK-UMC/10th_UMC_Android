package com.sungs.myapplication.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sungs.myapplication.R
import com.sungs.myapplication.navigation.AppDestination
import com.sungs.myapplication.ui.theme.Black
import com.sungs.myapplication.ui.theme.MyApplicationTheme
import com.sungs.myapplication.ui.theme.TextSecondary
import com.sungs.myapplication.ui.theme.White


enum class BottomTab(
    val destination: AppDestination,
    val iconRes: Int,
    val label: String
) {
    Home(AppDestination.Home, R.drawable.ic_nav_home, "홈"),
    Shop(AppDestination.Shop, R.drawable.ic_nav_shop, "구매하기"),
    Wishlist(AppDestination.Wishlist, R.drawable.ic_nav_wishlist, "위시리스트"),
    Bag(AppDestination.Bag, R.drawable.ic_nav_bag, "장바구니"),
    Profile(AppDestination.Profile, R.drawable.ic_nav_profile, "프로필"),
}

@Composable
fun AppBottomBar(
    selectedTab: BottomTab,
    onTabClick: (BottomTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
            .height(64.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BottomTab.entries.forEach { tab ->
            BottomBarItem(
                tab = tab,
                isSelected = tab == selectedTab,
                onClick = { onTabClick(tab) }
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    tab: BottomTab,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val tint = if (isSelected) Black else TextSecondary

    Column(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = tab.iconRes),
            contentDescription = tab.label,
            tint = tint,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = tab.label,
            fontSize = 11.sp,
            color = tint,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppBottomBarPreview() {
    MyApplicationTheme {
        AppBottomBar(
            selectedTab = BottomTab.Home,
            onTabClick = {}
        )
    }
}