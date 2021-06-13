package com.dicoding.movies.ListTv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.data.MoviesRepository
import com.dicoding.movies.Utils.DataDummy
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
class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var observer: Observer<List<TvShows>>

    @Before
    fun setUp(){
        viewModel = TvViewModel(moviesRepository)
    }

    @Test
    fun getTvShows(){
        val dummyTvShows = DataDummy.generateDummyTvShows()
        val listTvShowData = MutableLiveData<List<TvShows>>()
        listTvShowData.value = dummyTvShows

        Mockito.`when`(moviesRepository.getAllTvShows()).thenReturn(listTvShowData)
        viewModel.getTvShows()
        Mockito.verify(moviesRepository).getAllTvShows()

        val tvEntity = viewModel.getTvShows().value
        assertNotNull(tvEntity)
        assertEquals(4, tvEntity?.size)

        viewModel.getTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShows)
    }



}