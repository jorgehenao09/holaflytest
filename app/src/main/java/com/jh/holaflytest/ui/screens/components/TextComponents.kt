package com.jh.holaflytest.ui.screens.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

/**
 * Created by Jorge Henao on 14/11/23.
 */
@Composable
fun TitleText(
    modifier: Modifier,
    titleValue: String
) {
    Text(
        text = titleValue,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        modifier = modifier,
        color = MaterialTheme.colorScheme.primaryContainer
    )
}
