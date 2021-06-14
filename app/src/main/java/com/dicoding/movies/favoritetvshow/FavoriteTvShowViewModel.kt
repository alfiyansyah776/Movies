package com.dicoding.movies.favoritetvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.movies.data.MoviesRepository
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.data.source.local.entity.TvShows

class FavoriteTvShowViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getFavoritedTvShows(): LiveData<PagedList<TvShows>> =
        moviesRepository.getFavoritedTvShow()
}