package com.example.stocks.data.repo


import com.example.stocks.data.StockRepo
import com.example.stocks.data.network.ApiService
import com.example.stocks.data.dto.toLocal
import com.example.stocks.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultStockRepo @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
): StockRepo {
    override fun getStock(symbol: String) = flow {
        try {
            emit(Result.Loading)
            emit(Result.Success(apiService.getStockPrices(symbol).data.toLocal()))
        } catch (e: Exception){
            emit(Result.Error(e))
        }
    }.flowOn(dispatcher)
}

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}