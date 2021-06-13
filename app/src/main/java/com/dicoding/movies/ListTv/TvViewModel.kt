package com.dicoding.movies.ListTv

import androidx.lifecycle.ViewModel
import com.dicoding.movies.data.MoviesRepository

class TvViewModel (moviesRepository: MoviesRepository) : ViewModel() {
    val tvShows = moviesRepository.getAllTvShows()
}