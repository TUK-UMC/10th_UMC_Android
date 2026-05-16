package com.example.week7.ui.wishlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week7.ui.theme.NikeBlack
import com.example.week7.ui.theme.NikeWhite

@Composable
fun WishlistScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NikeWhite)
            .padding(start = 24.dp, top = 78.dp, end = 24.dp)
    ) {
        Text(
            text = "위시리스트",
            color = NikeBlack,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
