package com.nadafeteih.bookstore.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nadafeteih.bookstore.R

@Composable
fun ThemeSwitch(
    isDarkTheme: Boolean,
    onSwitch: () -> Unit
) {
    val moonImage = painterResource(R.drawable.icon_moon)
    val sunImage = painterResource(R.drawable.icon_sun)

    Box(
        modifier = Modifier.clickable(onClick = onSwitch)
    ) {
        Image(
            painter = if (isDarkTheme) moonImage else sunImage,
            contentDescription = if (isDarkTheme) "Dark" else "Light",
            modifier = Modifier.size(24.dp)
        )
    }
}