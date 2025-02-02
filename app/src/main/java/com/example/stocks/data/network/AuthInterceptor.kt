package com.example.stocks.data.network

import android.content.Context
import com.example.stocks.utils.SecureStorage
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = SecureStorage.getApiToken(context)

        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl: HttpUrl = originalUrl.newBuilder()
            .addQueryParameter("api_token", token)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        var response = chain.proceed(newRequest)

        if (response.code == 401) {
            synchronized(this) {
//                val newToken = tokenRefresher.refreshToken()
                // we can create this with refresh token or for first time with user details
                //right now it is static
                val newToken = "NBOahPa1fYRAeM8Dd4ffjIei8ynMc7KSkNKaWPdB"
                if (newToken != null) {
                    SecureStorage.saveApiToken(context, newToken)

                    val retriedUrl = originalUrl.newBuilder()
                        .addQueryParameter("api_token", newToken)
                        .build()

                    val retriedRequest = originalRequest.newBuilder()
                        .url(retriedUrl)
                        .build()

                    response.close()
                    response = chain.proceed(retriedRequest)
                }
            }
        }

        return response
    }
}
