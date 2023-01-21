package com.nadafeteih.bookstore.ui.composable

import com.nadafeteih.bookstore.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
) {

    object Home : BottomBarScreen(
        route = "Home",
        title = "Home",
        icon = R.drawable.home_icon,
    )

    object Saved : BottomBarScreen(
        route = "Saved",
        title = "Saved",
        icon = R.drawable.saved,
    )

    object Search : BottomBarScreen(
        route = "Search",
        title = "Search",
        icon = R.drawable.search_icon,
    )

}