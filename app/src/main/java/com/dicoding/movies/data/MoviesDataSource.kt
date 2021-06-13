package com.dicoding.movies.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.vo.Resource


interface MoviesDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<Movies>>>

    fun getAllTvShows(): LiveData<Resource<PagedList<TvShows>>>

    fun getFavoritedMovies(): LiveData<PagedList<Movies>>

    fun getFavoritedTvShow(): LiveData<PagedList<TvShows>>

    fun setFavoriteMovie(movies: Movies, state: Boolean)

    fun setFavoriteTvShow(tvShows: TvShows, state: Boolean)



}