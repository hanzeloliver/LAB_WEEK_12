package com.example.movielist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movielist.api.MovieService
import com.example.movielist.model.Movie
import com.example.movielist.model.PopularMoviesResponse

class MovieRepository(private val movieService: MovieService) {

    private val apiKey = "08e2b9471025fcb7f36e026788df6889"

    private val movieLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = movieLiveData

    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String>
        get() = errorLiveData

    suspend fun fetchMovies() {
        try {
            val popularMovies = movieService.getPopularMovies(apiKey)
            movieLiveData.postValue(popularMovies.results)
        } catch (e: Exception) {
            errorLiveData.postValue("An error occurred: ${e.message}")
        }
    }
}
