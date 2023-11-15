package com.jh.holaflytest.ui.screens.superHeroComics

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jh.holaflytest.ui.navigation.Screens
import com.jh.holaflytest.ui.viewModel.SuperHeroComicsViewModel

/**
 * Created by Jorge Henao on 15/11/23.
 */
@Composable
fun SuperHeroComicsScreen(
    superHeroComicsViewModel: SuperHeroComicsViewModel,
    superHeroId: Long,
    superHeroName: String,
    onNavigateScreen: (String) -> Unit
) {
    val superHeroComicsState by superHeroComicsViewModel.superHeroComicsState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        superHeroComicsViewModel.getSuperHeroComics(superHeroId)
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
            SuperHeroComicsHeader(superHeroName)
        }
        item {
            SuperHeroComicsSection(superHeroComicsState) { comicId: Long ->
                onNavigateScreen.invoke(Screens.ComicDetails.name + "/$comicId")
            }
        }
    }
}
