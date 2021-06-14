package com.dicoding.movies.ListTv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.data.MoviesRepository
import com.dicoding.movies.Utils.DataDummy
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
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(4)
        val listTvShowData = MutableLiveData<Resource<PagedList<TvShows>>>()
        listTvShowData.value = dummyTvShows

        `when`(moviesRepository.getAllTvShows()).thenReturn(listTvShowData)
        viewModel.tvShows
        Mockito.verify(moviesRepository).getAllTvShows()

        val tvEntity = viewModel.tvShows.value
        assertNotNull(tvEntity)
        assertEquals(4, tvEntity?.data?.size)

        viewModel.tvShows.observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShows)
    }



}