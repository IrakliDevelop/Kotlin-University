package com.example.cuproject.services

import com.example.cuproject.dto.movie.MovieResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import java.util.concurrent.TimeUnit
import retrofit2.http.GET


interface ApiInterface {

    @GET("/nikoloz14/movies-db/db")
    fun fetchAll(): Call<MovieResponse>

}

object ApiUtils {

    private const val BASE_URL = "https://my-json-server.typicode.com"

    val apiService: ApiInterface
        get() = RetrofitClient.getClient(BASE_URL)!!.create(ApiInterface::class.java)

}

object RetrofitClient {

    var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit? {
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
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