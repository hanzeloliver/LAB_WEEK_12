package com.example.test_lab_week_12.model

data class Movie(
    val id: Int,
    val title: String,
    val popularity: Double,
    val releaseDate: String?,
    val overview: String,
    val posterPath: String?
)
