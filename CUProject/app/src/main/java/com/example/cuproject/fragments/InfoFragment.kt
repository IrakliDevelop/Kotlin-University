package com.example.cuproject.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_info.*

import com.example.cuproject.R
import com.example.cuproject.dto.movie.Movie
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_info.view.*

/**
 * A simple [Fragment] subclass.
 */
class InfoFragment(private val movie: Movie) : Fragment() {

    companion object {

        fun newInstance(movie: Movie): InfoFragment {
            val fragment = InfoFragment(movie)
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_info, container, false)
        val movieInfo = movie
        Picasso.get().load(movieInfo.imageUrl).into(view.movieInfoImage)
        Log.d("title", movieInfo.title)
        view.OriginalTitleValue.text = movieInfo.title
        view.ReleaseDateValue.text = movieInfo.date
        view.LanguageValue.text = movieInfo.language
        view.SeasonsValue.text = movieInfo.seasons.toString()

        return view
    }


}
