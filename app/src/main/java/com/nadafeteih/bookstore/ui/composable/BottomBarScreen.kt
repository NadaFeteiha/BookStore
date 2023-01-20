package com.nadafeteih.bookstore.ui.composable

sealed class BottomBarScreen(
    val route: String,
    val title: String,
//    val icon: Int,
//    val icon_focused: Int
) {

    object Home: BottomBarScreen(
        route = "Home",
        title = "Home",
//        icon = R.drawable.ic_bottom_home,
//        icon_focused = R.drawable.ic_bottom_home_focused
    )

    object Saved: BottomBarScreen(
        route = "Saved",
        title = "Saved",
//        icon = R.drawable.ic_bottom_report,
//        icon_focused = R.drawable.ic_bottom_report_focused
    )

    // for report
    object Search: BottomBarScreen(
        route = "Search",
        title = "Search",
//        icon = R.drawable.ic_bottom_profile,
//        icon_focused = R.drawable.ic_bottom_profile_focused
    )

}