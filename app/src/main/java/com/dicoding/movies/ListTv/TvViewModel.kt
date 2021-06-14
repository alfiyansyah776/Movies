package com.dicoding.movies.ListTv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.movies.data.MoviesRepository
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.vo.Resource

class TvViewModel (private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getTvShows(): LiveData<Resource<PagedList<TvShows>>> =
        moviesRepository.getAllTvShows()
}