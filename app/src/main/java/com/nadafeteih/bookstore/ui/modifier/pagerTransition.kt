package com.nadafeteih.bookstore.ui.modifier

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.pagerTransition(page: Int, pagerState: PagerState) = graphicsLayer {
    val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
    if (pageOffset < -1f) {
        alpha = 0f
    } else if (pageOffset <= 0) {
        alpha = 1f
        transformOrigin = TransformOrigin(0f, 0.5f)
        rotationY = 90f * pageOffset.absoluteValue

    } else if (pageOffset <= 1) {
        alpha = 1f
        transformOrigin = TransformOrigin(1f, 0.5f)
        rotationY = -90f * pageOffset.absoluteValue
    } else {
        alpha = 0f
    }
}