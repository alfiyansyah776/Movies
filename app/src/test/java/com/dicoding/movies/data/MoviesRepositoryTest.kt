package com.dicoding.movies.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.movies.data.source.remote.RemoteDataSource
import com.dicoding.movies.Utils.DataDummy
import com.dicoding.movies.utils.LiveDataTestUtil
import org.junit.Test
import org.mockito.Mockito
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.doAnswer
import junit.framework.Assert.*
import org.junit.Rule

class MoviesRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val moviesRepository = FakeMoviesRepository(remote)

    private val movieResponse = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponse[0].id
    private val tvShowResponse = DataDummy.generateRemoteTvShows()
    private val tvId = tvShowResponse[0].id


    @Test
    fun getAllMovies (){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponse)
            null
        }.`when`(remote).getAllMovies(any())
        val moviesEntities = LiveDataTestUtil.getValue(moviesRepository.getAllMovies())
        verify(remote).getAllMovies(any())
        assertNotNull(moviesEntities)
        assertEquals(movieResponse.size.toLong(), moviesEntities.size.toLong())
    }

    @Test
    fun getAllTvShows () {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(tvShowResponse)
            null
        }.`when`(remote).getAllTvShows(any())
        val tvShowEntities = LiveDataTestUtil.getValue(moviesRepository.getAllTvShows())
        verify(remote).getAllTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getMoviesById (){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                    .onAllMoviesReceived(movieResponse)
            null
        }.`when`(remote).getAllMovies(any())
        val moviesEntities = LiveDataTestUtil.getValue(moviesRepository.getMoviesWithId(movieId))
        verify(remote).getAllMovies(any())
        assertNotNull(moviesEntities)
        assertNotNull(moviesEntities.title)
        assertEquals(movieResponse[0].title, moviesEntities.title)



    }

    @Test
    fun getTvById(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                    .onAllTvShowReceived(tvShowResponse)
            null
        }.`when`(remote).getAllTvShows(any())
        val tvEntities = LiveDataTestUtil.getValue(moviesRepository.getTvShowsWithId(tvId))
        verify(remote).getAllTvShows(any())
        assertNotNull(tvEntities)
        assertNotNull(tvEntities.title)
        assertEquals(tvShowResponse[0].originalName, tvEntities.title)


    }
}