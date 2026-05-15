package com.sungs.myapplication.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sungs.myapplication.ui.theme.Black
import com.sungs.myapplication.ui.theme.MyApplicationTheme
import com.sungs.myapplication.ui.theme.TextSecondary
import com.sungs.myapplication.ui.theme.White

private val shopTabs = listOf("전체", "Tops & T-Shirts", "Shoes")

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .padding(top = 16.dp)
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            containerColor = White,
            contentColor = Black,
            edgePadding = 16.dp,
        ) {
            shopTabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontSize = 14.sp,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTab == index) Black else TextSecondary
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShopScreenPreview() {
    MyApplicationTheme { ShopScreen() }
}