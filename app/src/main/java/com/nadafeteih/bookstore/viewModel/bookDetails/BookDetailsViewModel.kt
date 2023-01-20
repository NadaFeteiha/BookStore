package com.nadafeteih.bookstore.viewModel.bookDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteih.bookstore.useCase.GetBookDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookDetailsViewModel @Inject constructor(private val getBookDetails: GetBookDetailsUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(BookUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getBookDetailsById()
    }

    private fun getBookDetailsById() {
        _uiState.update { it.copy(isLoading = true, error = -1) }
        viewModelScope.launch {
            try {
                val book = getBookDetails(uiState.value.id)
                _uiState.update { it.copy(isLoading = true, bookDetail = book.toUIState()) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = 1) }
            }
        }
    }


}