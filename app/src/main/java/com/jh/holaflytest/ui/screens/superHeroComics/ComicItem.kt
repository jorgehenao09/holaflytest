package com.jh.holaflytest.ui.screens.superHeroComics

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jh.holaflytest.domain.model.comics.Comic
import com.jh.holaflytest.ui.screens.components.Thumbnail

/**
 * Created by Jorge Henao on 15/11/23.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComicItem(
    comic: Comic,
    comicSelected: (comicId: String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(end = 10.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        onClick = {
            comicSelected(comic.id ?: "")
        }
    ) {
        ConstraintLayout(
            Modifier.fillMaxSize()
        ) {
            val (image, name) = createRefs()

            Thumbnail(
                modifier = Modifier.constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
                imageUrl = comic.thumbnail?.url!!
            )
            Text(
                modifier = Modifier
                    .constrainAs(name) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(8.dp),
                text = comic.title ?: "",
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
