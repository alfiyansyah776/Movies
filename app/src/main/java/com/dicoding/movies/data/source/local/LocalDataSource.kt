package com.dicoding.movies.data.source.local

import androidx.paging.DataSource
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.data.source.local.room.MoviesDao

class LocalDataSource private constructor(private val mMoviesDao : MoviesDao){

    companion object{
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(moviesDao: MoviesDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(moviesDao)
    }

    fun getAllMovies(): DataSource.Factory<Int, Movies> = mMoviesDao.getMovies()

    fun getAllTvShows(): DataSource.Factory<Int, TvShows> = mMoviesDao.geTvShow()

    fun getFavoritedMovies(): DataSource.Factory<Int, Movies> = mMoviesDao.getFavoritedMovie()

    fun getFavoritedTvShows(): DataSource.Factory<Int, TvShows> = mMoviesDao.getFavoritedTvShow()

    fun insertMovies(movies: List<Movies>) = mMoviesDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShows>) = mMoviesDao.insertTvShows(tvShows)


    fun setMoviesFavorite(movies: Movies, newState : Boolean){
        movies.favorite = newState
        mMoviesDao.updateMovies(movies)
    }

    fun setTvShowFavorite(tvShows: TvShows, newState : Boolean){
        tvShows.favorite = newState
        mMoviesDao.updateTvShows(tvShows)
    }
    

}