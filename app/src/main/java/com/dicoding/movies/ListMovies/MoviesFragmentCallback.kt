package com.dicoding.movies.ListMovies

import com.dicoding.movies.data.source.local.entity.Movies


interface MoviesFragmentCallback {
    fun onShareClick(movies: Movies)
}