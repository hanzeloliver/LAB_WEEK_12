package com.example.test_lab_week_12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.movie_list)
        recyclerView.adapter = movieAdapter

        val movieRepository = (application as MovieApplication).movieRepository

        val movieViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MovieViewModel(movieRepository) as T
                }
            }
        )[MovieViewModel::class.java]

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                // FLOW — collect movies
                launch {
                    movieViewModel.popularMovies.collect { movies ->
                        val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString()

                        movieAdapter.addMovies(
                            movies.filter { it.releaseDate?.startsWith(currentYear) == true }
                                .sortedByDescending { it.popularity }
                        )
                    }
                }

                // FLOW — collect error
                launch {
                    movieViewModel.error.collect { errorMessage ->
                        if (errorMessage.isNotEmpty()) {
                            Snackbar.make(recyclerView, errorMessage, Snackbar.LENGTH_LONG).show()
                        }
                    }
                }

            }
        }
    }
}
