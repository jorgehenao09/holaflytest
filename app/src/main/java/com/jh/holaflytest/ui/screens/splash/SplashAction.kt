package com.jh.holaflytest.ui.screens.splash

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jh.holaflytest.R
import com.jh.holaflytest.ui.navigation.Screens

/**
 * Created by Jorge Henao on 14/11/23.
 */
@Composable
fun SplashAction(onNavigateScreen: (String) -> Unit) {
    ElevatedButton(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onSurface),
        onClick = { onNavigateScreen(Screens.HomeScreen.name) }
    ) {
        Text(
            stringResource(id = R.string.splash_start),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
