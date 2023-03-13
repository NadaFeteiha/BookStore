package com.nadafeteih.bookstore.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nadafeteih.bookstore.ui.screen.home.compose.ThemeSwitch
import com.nadafeteih.bookstore.ui.theme.Typography

@Composable
fun AppBar(
    title: Int,
    isHome: Boolean = true,
    appTheme: MutableState<Boolean> = mutableStateOf(true),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            style = Typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        if (isHome) {
            val checkedState = remember { mutableStateOf(appTheme.value) }
            ThemeSwitch(
                isDarkTheme = checkedState.value,
                onSwitch = {
                    checkedState.value = !checkedState.value
                    appTheme.value = checkedState.value
                }
            )
        }
    }
}