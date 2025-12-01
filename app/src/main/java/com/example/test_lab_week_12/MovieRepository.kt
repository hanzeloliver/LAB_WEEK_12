package com.example.test_lab_week_12.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_lab_week_12.api.MovieService
import com.example.test_lab_week_12.model.Movie
import com.example.test_lab_week_12.model.PopularMoviesResponse

class MovieRepository(private val movieService: MovieService) {

    public val apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOGUyYjk0NzEwMjVmY2I3ZjM2ZTAyNjc4OGRmNjg4OSIsIm5iZiI6MTc0OTMxNDEzMS4yMjMsInN1YiI6IjY4NDQ2YTUzM2IyZjY4MTVmOWRlNjg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.D3FVueFjYFRJMzS94TEO3-XdymgN-LBMYyVAMwTac_0"

    private val movieLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = movieLiveData

    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String> get() = errorLiveData

    suspend fun fetchMovies() {
        try {
            val response = movieService.getPopularMovies(apiKey)
            movieLiveData.postValue(response.results)
        } catch (e: Exception) {
            errorLiveData.postValue("Error: ${e.message}")
        }
    }
}
