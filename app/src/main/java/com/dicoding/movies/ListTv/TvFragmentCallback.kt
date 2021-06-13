package com.dicoding.movies.ListTv

import com.dicoding.movies.data.source.local.entity.TvShows


interface TvFragmentCallback {
    fun onShareClick(tvShows: TvShows)
}