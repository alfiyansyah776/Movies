package com.dicoding.movies.FavoriteMovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.movies.Utils.DataDummy
import com.dicoding.movies.data.MoviesRepository
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.favoritemovies.FavoriteMoviesViewModel
import com.dicoding.movies.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieTest {
    private lateinit var viewModel : FavoriteMoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var pagedList: PagedList<Movies>

    @Mock
    private lateinit var observer : Observer<PagedList<Movies>>

    @Before
    fun setUp(){
        viewModel = FavoriteMoviesViewModel(moviesRepository)
    }

    @Test
    fun getFavoriteMovies(){
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(3)
        val favoriteMovies = MutableLiveData<PagedList<Movies>>()
        favoriteMovies.value = dummyMovies

        `when`(moviesRepository.getFavoritedMovies()).thenReturn(favoriteMovies)
        val movies = viewModel.favoriteMovies.value
        verify(moviesRepository).getFavoritedMovies()
        assertNotNull(movies)
        assertEquals(3, movies?.size)

        viewModel.favoriteMovies.observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}