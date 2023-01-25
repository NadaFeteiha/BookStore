package com.nadafeteih.bookstore.viewModel.searchBook

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteih.bookstore.useCase.SearchBookUseCase
import com.nadafeteih.bookstore.viewModel.home.BooksUIState
import com.nadafeteih.bookstore.viewModel.home.toUIState
import com.nadafeteih.bookstore.viewModel.savedBook.SearchUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.AbstractQueue
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val searchBook: SearchBookUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUIState())
    val uiState = _uiState.asStateFlow()
    private var searchJob: Job? = null

    fun onQueryChange(queue: String) {
        _uiState.update { it.copy(isLoading = true, query = queue, error = -1) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1500)
            try {
                val searchResult = searchBook(uiState.value.query.trim())
                _uiState.update { it.copy(books = searchResult.toUIState(), isLoading = false) }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(isLoading = false, error = 1, books = emptyList())
                }
            }
        }
    }

}