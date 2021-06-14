package com.dicoding.movies.Detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.data.MoviesRepository
import com.dicoding.movies.Utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovies = DataDummy.generateDummyMovies()[0]
    private val dummyTvShows = DataDummy.generateDummyTvShows()[0]


    @Mock
    private lateinit var moviesRepository: MoviesRepository


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvObserver: Observer<TvShows>

    @Mock
    private lateinit var moviesObserver : Observer<Movies>


    @Before
    fun setUp(){
        viewModel = DetailViewModel(moviesRepository)

    }

    @Test
    fun setFavoriteMovies(){
        viewModel.setFavoriteMovies(dummyMovies, true)
        verify(moviesRepository).setFavoriteMovie(dummyMovies, true)

    }

    @Test
    fun setFavoriteTvShows(){
        viewModel.setFavoriteTvShows(dummyTvShows, true)
        verify(moviesRepository).setFavoriteTvShow(dummyTvShows, true)

    }



}