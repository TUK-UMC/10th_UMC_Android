package com.sungs.myapplication.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sungs.myapplication.ui.theme.Black
import com.sungs.myapplication.ui.theme.MyApplicationTheme
import com.sungs.myapplication.ui.theme.White

@Composable
fun ProfileEditScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "프로필 수정 화면", color = Black)
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileEditScreenPreview() {
    MyApplicationTheme { ProfileEditScreen() }
}