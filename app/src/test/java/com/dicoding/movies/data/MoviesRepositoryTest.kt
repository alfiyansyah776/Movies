package com.dicoding.movies.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.movies.Utils.AppExecutors
import com.dicoding.movies.data.source.remote.RemoteDataSource
import com.dicoding.movies.Utils.DataDummy
import com.dicoding.movies.data.source.local.LocalDataSource
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.utils.LiveDataTestUtil
import com.dicoding.movies.utils.PagedListUtils
import com.dicoding.movies.vo.Resource
import org.junit.Test
import org.mockito.Mockito
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.doAnswer
import junit.framework.Assert.*
import org.junit.Rule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MoviesRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)

    private val moviesRepository = FakeMoviesRepository(remote, local, appExecutors)
    private val movieResponse = DataDummy.generateRemoteDummyMovies()
    private val tvShowResponse = DataDummy.generateRemoteTvShows()



    @Test
    fun getAllMovies (){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movies>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        moviesRepository.getAllMovies()

        val moviesEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllMovies()
        assertNotNull(moviesEntities.data)
        assertEquals(movieResponse.size.toLong(), moviesEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows () {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShows>
        `when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        moviesRepository.getAllTvShows()

        val tvShowEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteMovies(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movies>
        `when`(local.getFavoritedMovies()).thenReturn(dataSourceFactory)
        moviesRepository.getFavoritedMovies()

        val moviesEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavoritedMovies()
        assertNotNull(moviesEntities)
        assertEquals(movieResponse.size.toLong(), moviesEntities.data?.size?.toLong())

    }

    @Test
    fun getFavoriteTvShows(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShows>
        `when`(local.getFavoritedTvShows()).thenReturn(dataSourceFactory)
        moviesRepository.getFavoritedTvShow()

        val tvShowEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getFavoritedTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.data?.size?.toLong())

    }
}