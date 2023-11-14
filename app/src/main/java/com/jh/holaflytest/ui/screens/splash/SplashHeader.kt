package com.jh.holaflytest.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jh.holaflytest.R

/**
 * Created by Jorge Henao on 14/11/23.
 */
@Composable
fun SplashHeader() {
    Image(
        painter = painterResource(id = R.drawable.marvel_avengers),
        contentDescription = "splash_image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(top = 100.dp)
            .size(300.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}
