package com.example.stocks.data

import com.example.stocks.data.repo.Result
import com.example.stocks.ui.model.Stock
import kotlinx.coroutines.flow.Flow

interface StockRepo {
   fun getStock(symbol: String): Flow<Result<List<Stock>>>
}