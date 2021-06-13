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
    private val movieId = dummyMovies.moviesId
    private val dummyTvShows = DataDummy.generateDummyTvShows()[0]
    private val tvId = dummyTvShows.tvId


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
        viewModel.setSelectedMovies(movieId)
        viewModel.setSelectedTvShows(tvId)
    }

    @Test
    fun getMovies(){
        val moviesData = MutableLiveData<Movies>()
        moviesData.value = dummyMovies

        Mockito.`when`(moviesRepository.getMoviesWithId(movieId)).thenReturn(moviesData)
        val movieEntity = viewModel.getMovies().value as Movies
        verify(moviesRepository).getMoviesWithId(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovies.title, movieEntity.title)
        assertEquals(dummyMovies.synopsis, movieEntity.synopsis)
        assertEquals(dummyMovies.aired, movieEntity.aired)
        assertEquals(dummyMovies.language, movieEntity.language)
        assertEquals(dummyMovies.poster, movieEntity.poster)
        assertEquals(dummyMovies.score, movieEntity.score)

        viewModel.getMovies().observeForever(moviesObserver)
        verify(moviesObserver).onChanged(dummyMovies)
    }

    @Test
    fun getTv(){
        val tvShowData = MutableLiveData<TvShows>()
        tvShowData.value = dummyTvShows

        Mockito.`when`(moviesRepository.getTvShowsWithId(tvId)).thenReturn(tvShowData)
        val tvEntity = viewModel.getTvShows().value as TvShows
        verify(moviesRepository).getTvShowsWithId(tvId)
        assertNotNull(tvEntity)
        assertEquals(dummyTvShows.title, tvEntity.title)
        assertEquals(dummyTvShows.synopsis, tvEntity.synopsis)
        assertEquals(dummyTvShows.aired, tvEntity.aired)
        assertEquals(dummyTvShows.language, tvEntity.language)
        assertEquals(dummyTvShows.poster, tvEntity.poster)
        assertEquals(dummyTvShows.score, tvEntity.score)

        viewModel.getTvShows().observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTvShows)
    }

}