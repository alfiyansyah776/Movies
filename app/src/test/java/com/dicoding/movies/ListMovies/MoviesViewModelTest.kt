package com.dicoding.movies.ListMovies


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.data.MoviesRepository
import com.dicoding.movies.Utils.DataDummy
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var observer: Observer<List<Movies>>


    @Before
    fun setUp(){
        viewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    fun getMovies(){
        val dummyMovies = DataDummy.generateDummyMovies()
        val listMoviesData = MutableLiveData<List<Movies>>()
        listMoviesData.value = dummyMovies

        `when`(moviesRepository.getAllMovies()).thenReturn(listMoviesData)
        viewModel.getMovies()
        verify(moviesRepository).getAllMovies()

        val moviesEntity = viewModel.getMovies().value
        assertNotNull(moviesEntity)
        assertEquals(3, moviesEntity?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)

    }


}