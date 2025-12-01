package com.example.test_lab_week_12

import com.example.test_lab_week_12.api.MovieService
import com.example.test_lab_week_12.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(private val movieService: MovieService) {

    private val apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOGUyYjk0NzEwMjVmY2I3ZjM2ZTAyNjc4OGRmNjg4OSIsIm5iZiI6MTc0OTMxNDEzMS4yMjMsInN1YiI6IjY4NDQ2YTUzM2IyZjY4MTVmOWRlNjg0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.D3FVueFjYFRJMzS94TEO3-XdymgN-LBMYyVAMwTac_0"

    // NOW using Flow instead of LiveData
    fun fetchMovies(): Flow<List<Movie>> {
        return flow {
            emit(movieService.getPopularMovies(apiKey).results)
        }.flowOn(Dispatchers.IO)
    }
}
