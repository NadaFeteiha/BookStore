package com.nadafeteih.bookstore.ui.screen.bookDetails

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.nadafeteih.bookstore.viewModel.bookDetails.BookDetailsArgs

private const val ROUTE = "BookDetailsRoute"

fun NavController.navigateToBookDetails(bookId: String) {
    navigate("${ROUTE}/${bookId}")
}

fun NavGraphBuilder.bookDetailsRoute(navController: NavHostController) {
    composable(
        route = "${ROUTE}/{${BookDetailsArgs.BOOK_ID}}",
        arguments = listOf(
            navArgument(BookDetailsArgs.BOOK_ID) { NavType.StringType }
        )) {
        BookDetailsScreen(navController)
    }
}