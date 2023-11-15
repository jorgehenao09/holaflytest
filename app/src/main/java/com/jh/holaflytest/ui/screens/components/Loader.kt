package com.jh.holaflytest.ui.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by Jorge Henao on 15/11/23.
 */
@Composable
fun Loader() {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .padding(start = 8.dp)
            .size(24.dp),
        strokeWidth = 2.dp
    )
}
