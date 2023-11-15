package com.jh.holaflytest.ui.screens.comicDetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jh.holaflytest.ui.screens.components.DescriptionText
import com.jh.holaflytest.ui.screens.components.TitleText
import com.jh.holaflytest.ui.state.ComicDetailStateUI

/**
 * Created by Jorge Henao on 15/11/23.
 */
@Composable
fun ComicDetailSection(
    comicDetailState: ComicDetailStateUI
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        AnimatedVisibility(visible = comicDetailState.isSuccess != null) {
            val comic = comicDetailState.isSuccess!![0]
            Column {
                TitleText(
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp),
                    titleValue = comic.title!!
                )

                DescriptionText(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 8.dp),
                    descriptionValue = comic.description?: "")
            }
        }
    }
}
