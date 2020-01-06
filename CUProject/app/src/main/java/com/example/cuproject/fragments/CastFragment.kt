package com.example.cuproject.fragments


import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.cuproject.R
import com.example.cuproject.adapters.CastRecyclerAdapter
import com.example.cuproject.dto.movie.Movie
import kotlinx.android.synthetic.main.fragment_cast.*

/**
 * A simple [Fragment] subclass.
 */
class CastFragment(private val movie: Movie) : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: CastRecyclerAdapter

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
        return inflater.inflate(R.layout.fragment_cast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(view.context)
        adapter = CastRecyclerAdapter(movie.cast)

        castListRecyclerView.layoutManager = linearLayoutManager
        castListRecyclerView.adapter = adapter
    }

}
