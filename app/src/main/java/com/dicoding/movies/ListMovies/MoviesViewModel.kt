package com.dicoding.movies.ListMovies

import androidx.lifecycle.ViewModel
import com.dicoding.movies.data.MoviesRepository


class MoviesViewModel(moviesRepository: MoviesRepository) : ViewModel() {

    val movie = moviesRepository.getAllMovies()
}