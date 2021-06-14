package com.dicoding.movies.FavoriteMovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.dicoding.movies.ListMovies.MoviesViewModelTest
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
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieTest {
    private lateinit var viewModel : FavoriteMoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository


    @Mock
    private lateinit var observer : Observer<PagedList<Movies>>

    @Before
    fun setUp(){
        viewModel = FavoriteMoviesViewModel(moviesRepository)
    }

    @Test
    fun getFavoriteMovies(){

        val expected = MutableLiveData<PagedList<Movies>>()
        expected.value = PagedTestDataSources.snapshot(DataDummy.generateDummyMovies())

        `when`(moviesRepository.getFavoritedMovies()).thenReturn(expected)

        viewModel.getFavoritedMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavoritedMovies().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)

    }

    class PagedTestDataSources private constructor(private val items: List<Movies>) : PositionalDataSource<Movies>(){
        companion object{
            fun snapshot(items: List<Movies> = listOf()) : PagedList<Movies> {
                return PagedList.Builder(PagedTestDataSources(items), 3)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Movies>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Movies>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }

    }



}