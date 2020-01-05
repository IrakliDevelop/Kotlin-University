package com.example.cuproject.dto.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("movies")
    val movies: List<Movie>
)

data class Movie(
    @SerializedName("id")
    val id: Number,
    @SerializedName("title")
    val title: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("seasons")
    val seasons: Number,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("cast")
    val cast: List<Cast>)

data class Cast(
    @SerializedName("id")
    val id: Number,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("imageUrl")
    val imageUrl: String)