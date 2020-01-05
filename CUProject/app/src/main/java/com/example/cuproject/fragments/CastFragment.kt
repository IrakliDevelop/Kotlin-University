package com.example.cuproject.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.cuproject.R
import com.example.cuproject.dto.movie.Movie
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class CastFragment(private val movie: Movie) : Fragment() {

    companion object {

        fun newInstance(movie: Movie): CastFragment {
            val fragment = CastFragment(movie)
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val movieCast = movie.cast
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cast, container, false)
    }


}
