package com.example.stocks.data.network

import com.example.stocks.data.dto.StockResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/data/quote")
    suspend fun getStockPrices(
        @Query("symbols") symbols: String
    ): StockResponse
}