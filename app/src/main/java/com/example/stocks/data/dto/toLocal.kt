package com.example.stocks.data.dto

import com.example.stocks.ui.model.Stock


fun StockDTO.toLocal() = Stock(
    ticker = ticker,
    name = name,
    price = price,
    day_change = day_change
)

@JvmName("networkToLocal")
fun List<StockDTO>.toLocal() = map(StockDTO::toLocal)
