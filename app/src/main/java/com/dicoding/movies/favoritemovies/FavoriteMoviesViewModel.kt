package com.dicoding.movies.favoritemovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.movies.data.MoviesRepository
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.vo.Resource

class FavoriteMoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getFavoritedMovies(): LiveData<PagedList<Movies>> =
        moviesRepository.getFavoritedMovies()
}