package com.example.stocks.di

import com.example.stocks.data.repo.DefaultStockRepo
import com.example.stocks.data.StockRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindTaskRepository(repository: DefaultStockRepo): StockRepo
}

