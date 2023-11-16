package com.jh.holaflytest.ui.screens.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import okhttp3.HttpUrl

/**
 * Created by Jorge Henao on 15/11/23.
 */
@Composable
fun Thumbnail(
    imageUrl: HttpUrl,
    showLoading: Boolean = true,
    modifier: Modifier = Modifier
) {
    var loading by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = modifier.size(120.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            onLoading = {
                loading = true
            },
            onSuccess = {
                loading = false
            },
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.DarkGray),
                        startY = size.height / 2,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                }
        )
        AnimatedVisibility(visible = loading && showLoading) {
            CircularProgressIndicator()
        }
    }
}
