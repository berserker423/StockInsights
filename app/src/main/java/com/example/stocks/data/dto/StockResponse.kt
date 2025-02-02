package com.example.stocks.data.dto

import androidx.annotation.Keep

@Keep
data class StockDTO(
    val ticker: String,
    val name: String,
    val mic_code: String?,
    val currency: String,
    val price: Double,
    val day_high: Double,
    val day_low: Double,
    val day_open: Double,
    val week_52_high: Double,
    val week_52_low: Double,
    val market_cap: Double?,
    val previous_close_price: Double,
    val previous_close_price_time: String,
    val day_change: Double,
    val volume: Int,
    val is_extended_hours_price: Boolean,
    val last_trade_time: String
)

data class StockResponse(
    val data: List<StockDTO>
)