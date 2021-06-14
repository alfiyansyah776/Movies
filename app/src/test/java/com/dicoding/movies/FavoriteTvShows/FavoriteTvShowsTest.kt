package com.dicoding.movies.FavoriteTvShows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.dicoding.movies.FavoriteMovies.FavoriteMovieTest
import com.dicoding.movies.Utils.DataDummy
import com.dicoding.movies.data.MoviesRepository
import com.dicoding.movies.data.source.local.entity.Movies
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
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowsTest {
    private lateinit var viewModel : FavoriteTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer : Observer<PagedList<TvShows>>


    @Before
    fun setUp(){
        viewModel = FavoriteTvShowViewModel(moviesRepository)
    }

    @Test
    fun getFavoriteTvShow(){
        val expected = MutableLiveData<PagedList<TvShows>>()
        expected.value = PagedTestDataSources.snapshot(DataDummy.generateDummyTvShows())

        `when`(moviesRepository.getFavoritedTvShow()).thenReturn(expected)

        viewModel.getFavoritedTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavoritedTvShows().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    class PagedTestDataSources private constructor(private val items: List<TvShows>) : PositionalDataSource<TvShows>(){
        companion object{
            fun snapshot(items: List<TvShows> = listOf()) : PagedList<TvShows> {
                return PagedList.Builder(PagedTestDataSources(items), 3)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TvShows>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TvShows>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }

    }
}