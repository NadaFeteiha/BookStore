package com.nadafeteih.bookstore.ui.screen.home.compose

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.nadafeteih.bookstore.entity.Book
import com.nadafeteih.bookstore.viewModel.BookUIState
import kotlin.math.abs
import kotlin.math.min


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookItem(
    movie: BookUIState,
    isSelected: Boolean,
    offset: Float,
) {
    val animateHeight = getOffsetBasedValue(
        selectedValue = 645,
        nonSelectedValue = 360,
        isSelected = isSelected,
        offset = offset
    ).dp
    val animateWidth = getOffsetBasedValue(
        selectedValue = 340,
        nonSelectedValue = 320,
        isSelected = isSelected,
        offset = offset
    ).dp
    val animateElevation = getOffsetBasedValue(
        selectedValue = 12,
        nonSelectedValue = 2,
        isSelected = isSelected,
        offset = offset
    ).dp

    val posterFullPath = "${movie.cover}"

    Card(
//        elevation = animateDpAsState(animateElevation).value,
        modifier = Modifier
            .width(animateWidth)
            .height(animateHeight)
            .padding(24.dp),
        shape = RoundedCornerShape(16.dp),
//        backgroundColor = MaterialTheme.colorScheme.onBackground,
//        contentColor = MaterialTheme.colorScheme.background,
//        onClick = { openMovieDetail.invoke() },
    ) {
        Column {
            Image(
                painter = rememberImagePainter(data = posterFullPath),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val clicked = remember { mutableStateOf(false) }
                Text(
                    text = movie.title,
                    modifier = Modifier.padding(8.dp),
                    style = typography.labelMedium
                )
                IconButton(onClick = {
//                    addToWatchList.invoke()
                    clicked.value = !clicked.value
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null,
                        modifier = Modifier
                            .graphicsLayer(
                                rotationY = animateFloatAsState(
                                    if (clicked.value) 720f else 0f, tween(400)
                                ).value
                            )
                    )
                }
            }

//            Text(
//                text = "Release: ${movie.release_date}",
//                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
//                style = typography.h6.copy(fontSize = 12.sp)
//            )
//            Text(
//                text = "PG13  •  ${movie.vote_average}/10",
//                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
//                style = typography.h6.copy(fontSize = 12.sp, fontWeight = FontWeight.Medium)
//            )
//            Text(
//                text = movie.overview,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis,
//                modifier = Modifier
//                    .align(Alignment.CenterHorizontally)
//                    .padding(8.dp)
//                    .weight(1f),
//                style = typography.subtitle2
//            )
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Get Tickets", modifier = Modifier.padding(8.dp))
            }
        }
    }
}

private fun getOffsetBasedValue(
    selectedValue: Int,
    nonSelectedValue: Int,
    isSelected: Boolean,
    offset: Float,
): Float {
    val actualOffset = if (isSelected) 1 - abs(offset) else abs(offset)
    val delta = abs(selectedValue - nonSelectedValue)
    val offsetBasedDelta = delta * actualOffset

    return min(selectedValue, nonSelectedValue) + offsetBasedDelta
}
