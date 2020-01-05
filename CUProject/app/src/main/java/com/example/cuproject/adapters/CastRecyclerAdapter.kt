package com.example.cuproject.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuproject.dto.movie.Cast

class CastRecyclerAdapter (private val cast: List<Cast>) : RecyclerView.Adapter<CastRecyclerAdapter.CastHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CastRecyclerAdapter.CastHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: CastRecyclerAdapter.CastHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount() = cast.size

    class CastHolder(v: View) : RecyclerView.ViewHolder(v)
}