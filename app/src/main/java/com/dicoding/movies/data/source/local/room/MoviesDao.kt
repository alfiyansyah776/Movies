package com.dicoding.movies.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.paging.DataSource
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.data.source.local.entity.TvShows


@Dao
interface MoviesDao {

    @Query("SELECT * FROM moviesentities")
    fun getMovies(): DataSource.Factory<Int, Movies>

    @Query("SELECT * FROM tvshowsentities")
    fun geTvShow(): DataSource.Factory<Int, TvShows>

    @Query("SELECT * FROM moviesentities where favorite = 1")
    fun getFavoritedMovie(): DataSource.Factory<Int, Movies>

    @Query("SELECT * FROM tvshowsentities where favorite = 1")
    fun getFavoritedTvShow(): DataSource.Factory<Int, TvShows>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies : List<Movies>)

    @Update
    fun updateMovies(movies: Movies)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShows>)

    @Update
    fun updateTvShows(tvShows: TvShows)


}