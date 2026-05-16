package com.example.week7.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week7.R
import com.example.week7.ui.theme.NikeBlack
import com.example.week7.ui.theme.NikeGray
import com.example.week7.ui.theme.NikeWhite

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NikeWhite)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 50.dp)
        ) {
            Text(
                text = stringResource(R.string.home_title),
                color = NikeBlack,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.home_date),
                color = NikeGray,
                fontSize = 16.sp
            )
        }
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = stringResource(R.string.home_image_description),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
}
