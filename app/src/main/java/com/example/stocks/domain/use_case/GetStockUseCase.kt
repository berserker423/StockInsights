package com.example.stocks.domain.use_case

import com.example.stocks.data.StockRepo
import javax.inject.Inject

class GetStockUseCase @Inject constructor(
    private val stockRepo: StockRepo
) {
    operator fun invoke(symbol: String)  = stockRepo.getStock(symbol)
}