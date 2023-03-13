package com.nadafeteih.bookstore.ui.screen.home.compose

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nadafeteih.bookstore.R
import com.nadafeteih.bookstore.ui.modifier.nonRippleEffect
import com.nadafeteih.bookstore.viewModel.home.BookUIState
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

    val animateHeight = getOffsetBasedValue(
        selectedValue = (screenHeight / 4) * 3,
        nonSelectedValue = (screenHeight / 2),
        isSelected = isSelected,
        offset = offset
    ).dp
    val animateWidth = getOffsetBasedValue(
        selectedValue = 340, nonSelectedValue = 320, isSelected = isSelected, offset = offset
    ).dp
    val animateElevation = getOffsetBasedValue(
        selectedValue = 12, nonSelectedValue = 2, isSelected = isSelected, offset = offset
    ).dp

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = animateDpAsState(animateElevation).value),
        modifier = Modifier
            .nonRippleEffect { onClickBook(state) }
            .width(animateWidth)
            .height(animateHeight)
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = state.cover),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
            )

            Text(
                text = state.title,
                maxLines = 2,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                color = MaterialTheme.colorScheme.primary,
                style = typography.titleLarge
            )

            Text(
                text = state.subTitle,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                style = typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 8.dp)
            ) {
                val clicked = remember { mutableStateOf(false) }

                Text(text = "price ${state.price}")

                Icon(
                    modifier = Modifier.nonRippleEffect {
                        onClickSaved(state)
                        clicked.value = !clicked.value
                    }, painter = painterResource(
                        id = if (state.isSaved) {
                            R.drawable.saved_icon
                        } else {
                            R.drawable.save_icon
                        }
                    ), tint = MaterialTheme.colorScheme.secondary, contentDescription = null
                )
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
