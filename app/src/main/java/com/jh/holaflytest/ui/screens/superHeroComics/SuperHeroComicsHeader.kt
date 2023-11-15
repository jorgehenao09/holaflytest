package com.jh.holaflytest.ui.screens.superHeroComics

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jh.holaflytest.ui.screens.components.TitleText

/**
 * Created by Jorge Henao on 15/11/23.
 */
@Composable
fun SuperHeroComicsHeader(superHeroName: String) {
    TitleText(
        modifier = Modifier.padding(start = 16.dp, top = 32.dp, bottom = 16.dp),
        titleValue = superHeroName
    )
}
