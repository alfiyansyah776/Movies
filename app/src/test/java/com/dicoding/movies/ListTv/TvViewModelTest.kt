package com.dicoding.movies.ListTv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.dicoding.movies.ListMovies.MoviesViewModelTest
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.data.MoviesRepository
import com.dicoding.movies.Utils.DataDummy
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.vo.Resource
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
class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var pagedList: PagedList<TvShows>


    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShows>>>

    @Before
    fun setUp(){
        viewModel = TvViewModel(moviesRepository)
    }

    @Test
    fun getTvShows(){
        val tvShows = PagedTestDataSources.snapshot(DataDummy.generateDummyTvShows())
        val expected = MutableLiveData<Resource<PagedList<TvShows>>>()
        expected.value = Resource.success(tvShows)

        `when`(moviesRepository.getAllTvShows()).thenReturn(expected)

        viewModel.getTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getTvShows().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)

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