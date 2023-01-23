package com.nadafeteih.bookstore.ui.screen.bookDetails.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.nadafeteih.bookstore.R
import java.lang.Math.floor

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
) {

    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - kotlin.math.ceil(rating)).toInt()

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }
        repeat(unfilledStars) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_outline_24),
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}