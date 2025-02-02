package com.example.stocks.ui.stock_view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocks.data.repo.Result
import com.example.stocks.ui.model.Stock
import com.example.stocks.domain.use_case.GetStockUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val getStockUseCase: GetStockUseCase
): ViewModel() {

    private val _uiState  = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()
    var lastSearch = "AAPL"

    fun fetchStock(symbol: String = lastSearch){
        lastSearch = symbol
        viewModelScope.launch {
            getStockUseCase(symbol).collectLatest {
                when(it){
                    is Result.Success -> {
                        _uiState.value = _uiState.value.copy(
                            stocks = it.data,
                            showError = it.data.isEmpty(),
                            errorText = if(it.data.isEmpty()) "Invalid Stock Symbol" else null,
                            showLoader = false
                        )
                    }
                    is Result.Error -> {
                        _uiState.value = _uiState.value.copy(
                            showError = true,
                            showLoader = false,
                            errorText = it.exception.message
                        )
                    }
                    is Result.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            showLoader = true,
                            showError = false,
                            errorText = null
                        )
                    }
                }

            }
        }
    }
}

data class UIState(
    val stocks: List<Stock> = emptyList(),
    val showError : Boolean = false,
    val showLoader : Boolean = true,
    val errorText: String? = null
)
