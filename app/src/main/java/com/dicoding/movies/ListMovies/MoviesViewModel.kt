package com.dicoding.movies.ListMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.movies.data.MoviesRepository
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.vo.Resource


class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

   fun getMovies(): LiveData<Resource<PagedList<Movies>>> =
       moviesRepository.getAllMovies()
}