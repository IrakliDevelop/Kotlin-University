package com.example.cuproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cuproject.R
import com.example.cuproject.dto.movie.Cast
import com.squareup.picasso.Picasso

class CastRecyclerAdapter (private val cast: List<Cast>) : RecyclerView.Adapter<CastRecyclerAdapter.CastHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CastHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cast_info, parent, false)
        return CastHolder(v)
    }

    override fun onBindViewHolder(holder: CastHolder, position: Int) {
        Picasso.get().load(cast[position].imageUrl).into(holder.actorImage)
        holder.actorFullName.text = cast[position].fullName
        holder.actorRole.text = cast[position].role
    }

    override fun getItemCount() = cast.size

    class CastHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val view = v
        var actorImage = view.findViewById<ImageView>(R.id.actorImage)!!
        var actorFullName = view.findViewById<TextView>(R.id.actorFullName)!!
        var actorRole = view.findViewById<TextView>(R.id.actorRole)!!


    }
}