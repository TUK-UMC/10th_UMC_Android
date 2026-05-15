package com.sungs.myapplication.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sungs.myapplication.ui.theme.Black
import com.sungs.myapplication.ui.theme.MyApplicationTheme
import com.sungs.myapplication.ui.theme.White

@Composable
fun WishlistScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Text(
            text = "위시리스트",
            color = Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WishlistScreenPreview() {
    MyApplicationTheme { WishlistScreen() }
}