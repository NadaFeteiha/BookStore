package com.nadafeteih.bookstore.ui.screen.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nadafeteih.bookstore.R
import com.nadafeteih.bookstore.ui.screen.bookDetails.navigateToBookDetails
import com.nadafeteih.bookstore.ui.screen.savedBook.compose.SavedBookItem
import com.nadafeteih.bookstore.viewModel.home.BookUIState
import com.nadafeteih.bookstore.viewModel.home.BooksUIState
import com.nadafeteih.bookstore.viewModel.savedBook.SearchUIState
import com.nadafeteih.bookstore.viewModel.searchBook.SearchViewModel


@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val systemUIController = rememberSystemUiController()
    systemUIController.setStatusBarColor(color = MaterialTheme.colorScheme.background)
    val state by viewModel.uiState.collectAsState()
    val clickedBook = remember { mutableStateOf(false) }

    SearchContent(
        state = state,
        onQueryChange = viewModel::onQueryChange,
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
fun SearchContent(
    state: SearchUIState,
    onQueryChange: (String) -> Unit,
    onClickBookDetails: (BookUIState) -> Unit
) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        stickyHeader {
            SearchBar(
                query = state.query,
                onQueryChange = onQueryChange,
                searching = state.isLoading,
            )
        }

        items(state.books, key = { it.id }) { book ->
            SavedBookItem(
                modifier = Modifier.animateItemPlacement(),
                book = book,
                onclickUnSave = {},
                onClickBookDetails = onClickBookDetails,
                isSaveIconVisible = false
            )
        }
    }


}


@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    searching: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
        ) {

            TextField(
                modifier = modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    textColor = MaterialTheme.colorScheme.primary
                ),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textDirection = TextDirection.Content
                ),
                value = query,
                shape = RoundedCornerShape(100.dp),
                singleLine = true,
                maxLines = 1,
                onValueChange = onQueryChange,
                placeholder = {
                    Text(
                        text = stringResource(R.string.search),
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontWeight = FontWeight.Normal,
                            textDirection = TextDirection.Content
                        )
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.search_icon),
                        tint = MaterialTheme.colorScheme.secondary,
                        contentDescription = null,
                    )
                },
            )

            if (searching) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .size(36.dp)
                )
            }
        }
    }
}