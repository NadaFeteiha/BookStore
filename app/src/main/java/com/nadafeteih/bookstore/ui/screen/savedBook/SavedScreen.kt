package com.nadafeteih.bookstore.ui.screen.savedBook

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nadafeteih.bookstore.R
import com.nadafeteih.bookstore.ui.screen.bookDetails.navigateToBookDetails
import com.nadafeteih.bookstore.ui.composable.AppBar
import com.nadafeteih.bookstore.ui.composable.HeightSpacer16
import com.nadafeteih.bookstore.ui.screen.savedBook.compose.SavedBookItem
import com.nadafeteih.bookstore.ui.screen.savedBook.compose.SavedBooks
import com.nadafeteih.bookstore.ui.theme.Typography
import com.nadafeteih.bookstore.viewModel.home.BookUIState
import com.nadafeteih.bookstore.viewModel.home.BooksUIState
import com.nadafeteih.bookstore.viewModel.savedBook.SavedBookViewModel


@Composable
fun SavedScreen(
    navController: NavHostController,
    viewModel: SavedBookViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val clickedBook = remember { mutableStateOf(false) }
    val systemUIController = rememberSystemUiController()
    systemUIController.setStatusBarColor(color = MaterialTheme.colorScheme.background)

    SavedContent(
        state = state,
        onClickSave = viewModel::onClickSave,
        onClickBookDetails = {
            if (!clickedBook.value) {
                navController.navigateToBookDetails(it.id)
            }
            clickedBook.value = !clickedBook.value
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SavedContent(
    state: BooksUIState,
    onClickSave: (BookUIState) -> Unit,
    onClickBookDetails: (BookUIState) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppBar(title = R.string.saved_book, isHome = false)

        if (state.books.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.books),
                    modifier = Modifier.size(64.dp),
                    contentDescription = null
                )

                HeightSpacer16()

                Text(
                    text = stringResource(id = R.string.empty_saved),
                    fontWeight = FontWeight.Bold,
                    style = Typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        } else {
            SavedBooks(
                state = state,
                onClickSave = onClickSave,
                onClickBookDetails = onClickBookDetails
            )
        }

    }
}