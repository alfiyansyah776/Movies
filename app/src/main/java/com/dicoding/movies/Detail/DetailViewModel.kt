package com.dicoding.movies.Detail

import android.graphics.Movie
import android.service.autofill.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.data.MoviesRepository
import com.dicoding.movies.vo.Resource

class DetailViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun setFavoriteMovies(movie: Movies, newStatus:Boolean) =
        moviesRepository.setFavoriteMovie(movie, newStatus)

    fun setFavoriteTvShows(tvShow: TvShows, newStatus: Boolean) =
        moviesRepository.setFavoriteTvShow(tvShow, newStatus)

        //tourismUseCase.setFavoriteTourism(tourism, newStatus)

}