package com.example.cuproject.dto.movie

data class Movie(
    val id: Number,
    val title: String,
    val date: String,
    val language: String,
    val seasons: Number,
    val imageUrl: String,
    val cast: Cast)

data class Cast(
    val id: Number,
    val fullName: String,
    val role: String,
    val imageUrl: String)