package com.example.cuproject.dto.movie

data class MovieResponse(
    val movies: List<Movie>
)

data class Movie(
    val id: Number,
    val title: String,
    val date: String,
    val language: String,
    val seasons: Number,
    val imageUrl: String,
    val cast: List<Cast>)

data class Cast(
    val id: Number,
    val fullName: String,
    val role: String,
    val imageUrl: String)