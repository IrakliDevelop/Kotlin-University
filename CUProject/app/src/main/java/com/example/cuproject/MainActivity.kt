package com.example.cuproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.cuproject.dto.movie.Movie
import com.example.cuproject.dto.movie.MovieResponse
import com.example.cuproject.services.ApiInterface
import com.example.cuproject.services.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("traki", "traki")
        var apiService: ApiInterface? = null

        //After oncreate

        apiService = ApiUtils.apiService

        val fetch = apiService.fetchAll()
        val data = MutableLiveData<List<Movie>>()

        fetch.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                Log.v("retrofit", "call failed")
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                data.value = response.body()!!.movies
                Log.d("retrofit", "success")
                Log.d("data", "${data.value}")
            }
        })

    }
}
