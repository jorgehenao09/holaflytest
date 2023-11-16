package com.jh.holaflytest.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jh.holaflytest.R
import com.jh.holaflytest.domain.model.MenuOption

/**
 * Created by Jorge Henao on 14/11/23.
 */
@Composable
fun SuperHeroOptionItem(
    option: MenuOption,
    superHeroSelected: (superHeroId: Long, superHeroName: String) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(start = 28.dp, end = 28.dp, top = 8.dp, bottom = 8.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    superHeroSelected(option.id, option.name)
                }
        ) {
            val (infoOption, moreInfo) = createRefs()

            Image(
                painter = painterResource(id = option.image),
                contentDescription = "superhero_image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(infoOption) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
            )

            Row(
                modifier = Modifier
                    .constrainAs(moreInfo) {
                        top.linkTo(infoOption.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    text = option.name,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelSmall,
                        text = stringResource(id = R.string.home_more_information)
                    )
                    Icon(
                        Icons.Filled.KeyboardArrowRight,
                        contentDescription = "chevron",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
