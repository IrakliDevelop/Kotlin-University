package com.example.cuproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cuproject.adapters.PagerAdapter
import com.example.cuproject.dto.movie.Movie
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val movie = Gson().fromJson(intent.getStringExtra("movie"), Movie::class.java)
        Log.d("movieInSecondActivity", "$movie")

        val fragmentAdapter = PagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        info_tab.setupWithViewPager(viewpager_main)
    }
}
