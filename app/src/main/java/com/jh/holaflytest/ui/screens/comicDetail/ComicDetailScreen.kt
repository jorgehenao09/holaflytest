package com.jh.holaflytest.ui.screens.comicDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jh.holaflytest.ui.viewModel.ComicDetailViewModel

/**
 * Created by Jorge Henao on 15/11/23.
 */
@Composable
fun ComicDetailScreen(
    comicDetailViewModel: ComicDetailViewModel,
    comicId: String
) {
    val superHeroComicsState by comicDetailViewModel.comicDetailState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        comicDetailViewModel.getComicDetail(comicId)
    }

    LazyColumn(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.onSecondary,
                    )
                )
            )
    ) {
        item {
            ComicDetailHeader(superHeroComicsState)
        }
        item {
            ComicDetailSection(superHeroComicsState)
        }
    }
}
