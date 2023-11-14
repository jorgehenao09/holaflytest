package com.jh.holaflytest.ui.screens.home

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
import com.jh.holaflytest.ui.viewModel.MenuOptionsViewModel

/**
 * Created by Jorge Henao on 14/11/23.
 */
@Composable
fun HomeScreen(
    menuOptionsViewModel: MenuOptionsViewModel,
    onNavigateScreen: (String) -> Unit
) {
    val menuOptionsState by menuOptionsViewModel.menuOptionsSate.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        menuOptionsViewModel.getMenuOptions()
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
            HomeHeader()
        }
        items(menuOptionsState.isSuccess?.options.orEmpty()) { option ->
            SuperHeroOptionItem(option) {
                onNavigateScreen.invoke(Screens.SuperHeroComics.name + "/$it")
            }
        }
    }
}
