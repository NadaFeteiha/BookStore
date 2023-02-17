package com.nadafeteih.bookstore.ui.screen.bookDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nadafeteih.bookstore.R
import com.nadafeteih.bookstore.ui.composable.HeightSpacer16
import com.nadafeteih.bookstore.ui.composable.HeightSpacer8
import com.nadafeteih.bookstore.ui.modifier.nonRippleEffect
import com.nadafeteih.bookstore.ui.screen.bookDetails.compose.RatingBar
import com.nadafeteih.bookstore.viewModel.bookDetails.BookDetailsUIState
import com.nadafeteih.bookstore.viewModel.bookDetails.BookDetailsViewModel
import com.nadafeteih.bookstore.viewModel.bookDetails.BookUIState


@Composable
fun BookDetailsScreen(
    navController: NavHostController,
    viewModel: BookDetailsViewModel = hiltViewModel(),
) {
    val systemUIController = rememberSystemUiController()
    systemUIController.setStatusBarColor(color = MaterialTheme.colorScheme.secondaryContainer)

    val state by viewModel.uiState.collectAsState()
    BookDetailsContent(
        state = state,
        onClickSaved = viewModel::onClickSave
    )
}


@Composable
fun BookDetailsContent(
    state: BookUIState,
    onClickSaved: (BookDetailsUIState) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp)
                )
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                painter = rememberAsyncImagePainter(model = state.bookDetail.cover),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = state.bookDetail.year)
                Text(text = state.bookDetail.pages)
                Text(text = state.bookDetail.price)
                Text(text = state.bookDetail.language)
            }

            HeightSpacer16()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RatingBar(rating = state.bookDetail.rating.toDoubleOrNull() ?: 0.0)

                Icon(
                    modifier = Modifier.nonRippleEffect { onClickSaved(state.bookDetail) },
                    painter = painterResource(
                        id = if (state.bookDetail.isSaved) {
                            R.drawable.saved_icon
                        } else {
                            R.drawable.save_icon
                        }
                    ),
                    contentDescription = null
                )
            }

            HeightSpacer16()
        }

        HeightSpacer16()
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                ) {
                    append("Authors: ")
                }
                withStyle(
                    style = SpanStyle(color = Color.Black),
                ) {
                    append(state.bookDetail.authors)
                }
            },
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )

        HeightSpacer16()
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Description:",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = HtmlCompat.fromHtml(
                state.bookDetail.description,
                HtmlCompat.FROM_HTML_MODE_COMPACT
            ).toString()
        )
        HeightSpacer16()
    }
}


