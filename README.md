
Stock Market App
================

📌 Overview
-----------
StockInsights is an Android app that fetches real-time stock data from the StockData.org API. 
Users can search for stocks by symbol and view:

- Stock Symbol & Company Name
- Current Price & Day's Change
- Trend Indicator (Up/Down arrow based on price change)

🛠️ Tech Stack
--------------
- Kotlin + XML UI
- MVVM Architecture
- Retrofit for API calls
- Kotlin Coroutines for async tasks
- EncryptedSharedPreferences for secure API key storage

🚀 Features
-----------
- Search stocks by symbol
- Pull-to-refresh for live updates
- Error handling for invalid symbols & network issues
- Live trend indicators

📁 API Details
--------------
- **Provider**: StockData.org
- **Endpoint**: https://api.stockdata.org/v1/data/quote
- **Security**: API key stored using EncryptedSharedPreferences
- **API Key Limitation**: The free API key allows 100 requests. After reaching the limit, you need to generate a new key from StockData.org.
