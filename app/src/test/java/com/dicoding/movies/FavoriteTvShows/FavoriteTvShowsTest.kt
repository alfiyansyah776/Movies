package com.dicoding.movies.FavoriteTvShows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.movies.data.MoviesRepository
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.favoritetvshow.FavoriteTvShowViewModel
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
class FavoriteTvShowsTest {
    private lateinit var viewModel : FavoriteTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer : Observer<PagedList<TvShows>>

    @Mock
    private lateinit var pagedList: PagedList<TvShows>

    @Before
    fun setUp(){
        viewModel = FavoriteTvShowViewModel(moviesRepository)
    }

    @Test
    fun getFavoriteTvShow(){
        val dummyTvShows = pagedList
        `when`(dummyTvShows.size).thenReturn(4)
        val favoriteTvShow = MutableLiveData<PagedList<TvShows>>()
        favoriteTvShow.value = dummyTvShows

        `when`(moviesRepository.getFavoritedTvShow()).thenReturn(favoriteTvShow)
        val movies = viewModel.favoriteTvShow.value
        verify(moviesRepository).getFavoritedMovies()
        assertNotNull(movies)
        assertEquals(4, movies?.size)

        viewModel.favoriteTvShow.observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}