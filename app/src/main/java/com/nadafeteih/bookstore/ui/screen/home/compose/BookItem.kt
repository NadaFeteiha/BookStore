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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nadafeteih.bookstore.viewModel.BookUIState
import kotlin.math.abs
import kotlin.math.min


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookItem(
    state: BookUIState,
    isSelected: Boolean,
    offset: Float,
    onClickBook: (BookUIState) -> Unit,
    onClickSaved: (BookUIState) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp.dp

    val animateHeight = getOffsetBasedValue(
        selectedValue = (screenHeight / 4) * 3,
        nonSelectedValue = (screenHeight / 2),
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

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = animateDpAsState(animateElevation).value),
        modifier = Modifier
            .width(animateWidth)
            .height(animateHeight)
            .padding(24.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = { onClickBook(state) },
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = state.cover),
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
                    text = state.title,
                    modifier = Modifier.padding(8.dp),
                    style = typography.labelMedium
                )
                IconButton(onClick = {
                    onClickSaved(state)
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
