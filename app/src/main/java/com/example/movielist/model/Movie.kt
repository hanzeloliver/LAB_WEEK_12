package com.example.movielist.model

data class Movie(
    val id: Int,
    val title: String,
    val popularity: Double,
    val releaseDate: String?,
    val overview: String,
    val poster_path: String?
)
