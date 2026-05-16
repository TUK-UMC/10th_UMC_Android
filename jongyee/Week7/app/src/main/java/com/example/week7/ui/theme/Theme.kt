package com.example.week7.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = NikeBlack,
    onPrimary = NikeWhite,
    background = NikeWhite,
    onBackground = NikeBlack,
    surface = NikeWhite,
    onSurface = NikeBlack,
)

@Composable
fun Week7Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
