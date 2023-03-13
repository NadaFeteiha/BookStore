package com.nadafeteih.bookstore.ui.screen.savedBook.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nadafeteih.bookstore.R
import com.nadafeteih.bookstore.ui.composable.HeightSpacer8
import com.nadafeteih.bookstore.ui.modifier.nonRippleEffect
import com.nadafeteih.bookstore.viewModel.home.BookUIState

@Composable
fun SavedBookItem(
    modifier: Modifier = Modifier,
    book: BookUIState,
    onClickBookDetails: (BookUIState) -> Unit,
    onclickUnSave: (BookUIState) -> Unit,
    isSaveIconVisible: Boolean = true
) {

    Row(
        modifier = modifier
            .nonRippleEffect { onClickBookDetails(book) }
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 8.dp)
            .padding(end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Image(
            modifier = Modifier.size(100.dp),
            painter = rememberAsyncImagePainter(model = book.cover),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(modifier = modifier.weight(1f)) {
            Text(text = book.title, maxLines = 2)
            HeightSpacer8()

            Text(text = book.subTitle, maxLines = 1)
            HeightSpacer8()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = book.price)

                if (isSaveIconVisible) {
                    Icon(
                        modifier = Modifier.nonRippleEffect { onclickUnSave(book) },
                        painter = painterResource(id = R.drawable.saved_icon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary,
                    )
                }
            }

        }
    }
}