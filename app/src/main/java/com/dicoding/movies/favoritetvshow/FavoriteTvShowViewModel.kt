package com.dicoding.movies.favoritetvshow

import androidx.lifecycle.ViewModel
import com.dicoding.movies.data.MoviesRepository

class FavoriteTvShowViewModel(moviesRepository: MoviesRepository) : ViewModel() {
    val favoriteTvShow = moviesRepository.getFavoritedTvShow()
}