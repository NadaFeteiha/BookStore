package com.nadafeteih.bookstore.ui.screen.home.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nadafeteih.bookstore.R
import com.nadafeteih.bookstore.ui.modifier.nonRippleEffect
import com.nadafeteih.bookstore.ui.modifier.pagerTransition
import com.nadafeteih.bookstore.viewModel.home.BookUIState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BookItem(
    state: BookUIState,
    page: Int,
    pagerState: PagerState,
    onClickBook: (BookUIState) -> Unit,
    onClickSaved: (BookUIState) -> Unit
) {
    Card(
        modifier = Modifier
            .nonRippleEffect { onClickBook(state) }
            .pagerTransition(page, pagerState)
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

                Text(
                    text = stringResource(R.string.price, state.price),
                    fontWeight = FontWeight.Bold,
                    style = typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )

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
                    ),
                    tint = MaterialTheme.colorScheme.tertiary,
                    contentDescription = null
                )
            }
        }
    }
}


