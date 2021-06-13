package com.dicoding.movies.favoritemovies

import androidx.lifecycle.ViewModel
import com.dicoding.movies.data.MoviesRepository

class FavoriteMoviesViewModel(moviesRepository: MoviesRepository) : ViewModel() {
    val favoriteMovies = moviesRepository.getFavoritedMovies()
}