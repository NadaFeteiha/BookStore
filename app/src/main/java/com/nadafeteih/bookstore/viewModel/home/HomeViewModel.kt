package com.nadafeteih.bookstore.viewModel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteih.bookstore.useCase.GetNewBooksUseCase
import com.nadafeteih.bookstore.viewModel.HomeUIState
import com.nadafeteih.bookstore.viewModel.toUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getNewBooks: GetNewBooksUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getBooks()
    }

    private fun getBooks() {
        _uiState.update { it.copy(isLoading = true, error = -1) }
        viewModelScope.launch {
            try {
                val books = getNewBooks()
                _uiState.update { it.copy(isLoading = false, books = books.toUIState()) }

            } catch (throwable: Throwable) {
                _uiState.update { it.copy(error = 1, isLoading = false) }
            }

        }
    }


}