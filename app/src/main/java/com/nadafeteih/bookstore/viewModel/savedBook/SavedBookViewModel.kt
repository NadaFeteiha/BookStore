package com.nadafeteih.bookstore.viewModel.savedBook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteih.bookstore.useCase.GetSavedBooksUseCase
import com.nadafeteih.bookstore.useCase.SaveBookUseCase
import com.nadafeteih.bookstore.viewModel.home.BookUIState
import com.nadafeteih.bookstore.viewModel.home.BooksUIState
import com.nadafeteih.bookstore.viewModel.home.toEntity
import com.nadafeteih.bookstore.viewModel.home.toUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedBookViewModel @Inject constructor(
    private val saveBook: SaveBookUseCase,
    private val getSavedBooks: GetSavedBooksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BooksUIState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                getSavedBooks().collect { books ->
                    _uiState.update { it.copy(books = books.toUIState()) }
                }
            }catch (t:Throwable){

            }
        }
    }

    fun onClickSave(book: BookUIState) {
        viewModelScope.launch {
            saveBook(book.toEntity(),true)
            _uiState.update {
                it.copy(books = _uiState.value.books
                    .map {if (it.id == book.id) { it.copy(isSaved = !book.isSaved) } else { it } }
                )
            }
        }
    }

}