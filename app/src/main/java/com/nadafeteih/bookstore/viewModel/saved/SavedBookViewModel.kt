package com.nadafeteih.bookstore.viewModel.saved

import androidx.lifecycle.ViewModel
import com.nadafeteih.bookstore.viewModel.bookDetails.BookUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SavedBookViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(BookUIState())
    val uiState = _uiState.asStateFlow()


}