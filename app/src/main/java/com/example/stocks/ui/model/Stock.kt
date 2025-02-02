package com.example.stocks.ui.model


data class Stock(
    val ticker: String,
    val name: String,
    val price: Double,
    val day_change: Double,
)