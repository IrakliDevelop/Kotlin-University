package com.example.cufinal.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiInterface {

    @GET("data/2.5/forecast?q=Tbilisi&APPID=a5ee34b7ab1ff3b8a71ae75183a1735e")
    fun fetchAll(): Call<*> // TODO: create models
}

object ApiUtils {
    private const val BASE_URL = "https://api.openweathermap.org"
}

object RetrofitClient {
    var retrofit: Retrofit? = null
    fun getClient(baseUrl: String): Retrofit? {
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client =OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}