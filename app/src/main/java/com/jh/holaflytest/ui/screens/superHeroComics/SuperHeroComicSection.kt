package com.jh.holaflytest.ui.screens.superHeroComics

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jh.holaflytest.ui.screens.components.Loader
import com.jh.holaflytest.ui.state.SuperHeroComicsStateUI

/**
 * Created by Jorge Henao on 15/11/23.
 */
@Composable
fun SuperHeroComicsSection(
    superHeroComicsState: SuperHeroComicsStateUI,
    comicSelected: (comicId: Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 16.dp)
    ) {
        AnimatedVisibility(
            visible = superHeroComicsState.isLoading,
        ) {
            Loader()
        }

        AnimatedVisibility(visible = superHeroComicsState.isSuccess != null) {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(top = 16.dp, start = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .heightIn(max = 400.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(superHeroComicsState.isSuccess.orEmpty()) { comic ->
                    ComicItem(comic) { comicId: Long ->
                        comicSelected.invoke(comicId)
                    }
                }
            }
        }
    }
}
