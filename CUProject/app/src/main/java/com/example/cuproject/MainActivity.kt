package com.example.cuproject

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuproject.dto.movie.Movie
import com.example.cuproject.dto.movie.MovieResponse
import com.example.cuproject.services.ApiInterface
import com.example.cuproject.services.ApiUtils
import com.example.cuproject.utils.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var apiService: ApiInterface? = null
        val spinner: ProgressBar = findViewById(R.id.progressBar1)
        linearLayoutManager = LinearLayoutManager(this)
        movieListRecyclerView.layoutManager = linearLayoutManager

        spinner.visibility = View.VISIBLE

        //After oncreate

        apiService = ApiUtils.apiService

        val fetch = apiService.fetchAll()
        val data = MutableLiveData<List<Movie>>()
        var movieList: List<Movie>

        fetch.enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                Log.v("retrofit", "call failed")
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                spinner.visibility = View.GONE
                data.value = response.body()!!.movies
                movieList = data.value!!
                // TODO: logic
                Log.d("retrofit", "success")
                Log.d("data", "${data.value}")
                Log.d("list", "${movieList?.get(0)?.cast?.get(0)}")
                movieListRecyclerView.adapter = RecyclerAdapter(movieList)
            }
        })

    }
}
