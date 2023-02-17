package com.nadafeteih.bookstore.viewModel.bookDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteih.bookstore.useCase.GetBookDetailsUseCase
import com.nadafeteih.bookstore.useCase.SaveBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val getBookDetails: GetBookDetailsUseCase,
    private val saveBook: SaveBookUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = BookDetailsArgs(savedStateHandle)

    private val _uiState = MutableStateFlow(BookUIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(bookDetail = it.bookDetail.copy(id = args.bookId)) }
        getBookDetailsById()
    }

    private fun getBookDetailsById() {
        _uiState.update { it.copy(isLoading = true, error = -1) }
        viewModelScope.launch {
            try {
                val book = getBookDetails(uiState.value.bookDetail.id)
                _uiState.update { it.copy(isLoading = true, bookDetail = book.toUIState()) }
            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = 1) }
            }
        }
    }

    fun onClickSave(book: BookDetailsUIState) {
        viewModelScope.launch {
            saveBook(book = book.toEntity(), book.isSaved)
            _uiState.update { it.copy(bookDetail = book.copy(isSaved = !it.bookDetail.isSaved)) }
        }
    }

}