package com.nadafeteih.bookstore.viewModel.searchBook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteih.bookstore.useCase.SearchBookUseCase
import com.nadafeteih.bookstore.viewModel.home.BookUIState
import com.nadafeteih.bookstore.viewModel.home.toUIState
import com.nadafeteih.bookstore.viewModel.savedBook.SearchUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val searchBook: SearchBookUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUIState())
    val uiState = _uiState.asStateFlow()

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _books = MutableStateFlow(emptyList<BookUIState>())
    val books = query
            .debounce(1000L)
            .onEach { _uiState.update { it.copy(isLoading = true) } }
            .combine(_books) { text, books ->
                if (text.isBlank()) {
                    books
                } else {
                    try {
                        val searchResult = searchBook(text.trim())
                        _uiState.update { it.copy(isLoading = false) }
                        _books.emit(searchResult.toUIState())
                        _books.value
                    } catch (t: Throwable) {
                        _uiState.update {
                            it.copy(isLoading = false, error = 1)
                        }
                        _books.value
                    }
                }
            }
            .onEach { _uiState.update { it.copy(isLoading = false) } }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                _books.value
            )

    fun onQueryChange(queue: String) {
        _uiState.update { it.copy(isLoading = true, error = -1) }
        _query.value = queue
    }

}