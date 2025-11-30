package com.example.movielist.model

data class PopularMoviesResponse(
    val page: Int,
    val results: List<Movie>
)
