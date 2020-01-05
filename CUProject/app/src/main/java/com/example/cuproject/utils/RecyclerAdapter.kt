package com.example.cuproject.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cuproject.R
import com.example.cuproject.dto.movie.Movie
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val movies: List<Movie>
) : RecyclerView.Adapter<RecyclerAdapter.MovieHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_info, parent, false)
        return MovieHolder(v)
    }

    override fun getItemCount() = movies.size


    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        Picasso.get().load(movies[position].imageUrl).into(holder.movieImage)
        holder.movieTitle.text = movies[position].title
    }

    //1
    class MovieHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        private var movie: Movie? = null
        var movieImage = view.findViewById<ImageView>(R.id.movieImage)!!
        var movieTitle = view.findViewById<TextView>(R.id.movieTitle)!!

        //3
        init {

            v.setOnClickListener(this)
        }

        //4
        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }

//        companion object {
//            //5
//            private val PHOTO_KEY = "PHOTO"
//        }
    }

}

