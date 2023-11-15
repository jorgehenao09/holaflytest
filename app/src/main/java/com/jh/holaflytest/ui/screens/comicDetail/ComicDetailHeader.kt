package com.jh.holaflytest.ui.screens.comicDetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.jh.holaflytest.ui.state.ComicDetailStateUI

/**
 * Created by Jorge Henao on 15/11/23.
 */
@Composable
fun ComicDetailHeader(
    comicDetailState: ComicDetailStateUI,
    showLoading: Boolean = true,
) {
    var loading by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AnimatedVisibility(visible = comicDetailState.isSuccess != null) {
            val comic = comicDetailState.isSuccess!![0]
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(comic.thumbnail!!.url)
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
                    .fillMaxWidth()
                    .height(370.dp)
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
        }
        AnimatedVisibility(visible = loading && showLoading) {
            CircularProgressIndicator()
        }
    }
}
