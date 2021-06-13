package com.dicoding.movies.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.data.source.remote.RemoteDataSource
import com.dicoding.movies.data.source.remote.response.ResultsMovies
import com.dicoding.movies.data.source.remote.response.ResultsTvShows

class FakeMoviesRepository (private val remoteDataSource: RemoteDataSource) : MoviesDataSource {

    override fun getAllMovies(): LiveData<List<Movies>> {
        val moviesResult = MutableLiveData<List<Movies>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesResponse: List<ResultsMovies>?) {
                val moviesList = ArrayList<Movies>()
                if (moviesResponse!= null) {
                    for (response in moviesResponse){
                        val movies = Movies(
                                response.id,
                                response.posterPath,
                                response.originalLanguage,
                                response.overview,
                                response.title,
                                response.releaseDate,
                                response.voteAverage)
                        moviesList.add(movies)

                    }

                }
                moviesResult.postValue(moviesList)
            }

        })


        return moviesResult
    }

    override fun getAllTvShows(): LiveData<List<TvShows>> {
        val tvResult = MutableLiveData<List<TvShows>>()
        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowCallback{
            override fun onAllTvShowReceived(tvShowsResponse: List<ResultsTvShows>?) {
                val tvShowList = ArrayList<TvShows>()
                if (tvShowsResponse != null) {
                    for (response in tvShowsResponse){
                        val tv = TvShows (
                                response.id,
                                response.posterPath,
                                response.originalLanguage,
                                response.overview,
                                response.originalName,
                                response.firstAirDate,
                                response.voteAverage)

                        tvShowList.add(tv)
                    }
                }

                tvResult.postValue(tvShowList)
            }

        })

        return tvResult
    }

    override fun getMoviesWithId(moviesId: Int): LiveData<Movies> {
        val movieWithIdResult = MutableLiveData<Movies>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesResponse : List<ResultsMovies>?) {
                var movies : Movies? = null
                if (moviesResponse != null) {
                    for (response in moviesResponse){
                        if (response.id == moviesId){
                            movies = Movies(
                                    response.id,
                                    response.posterPath,
                                    response.originalLanguage,
                                    response.overview,
                                    response.title,
                                    response.releaseDate,
                                    response.voteAverage)


                        }
                    }

                }
                movieWithIdResult.postValue(movies)
            }



        })

        return movieWithIdResult
    }

    override fun getTvShowsWithId(tvId: Int): LiveData<TvShows> {
        val tvWithResult = MutableLiveData<TvShows>()
        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowCallback{
            override fun onAllTvShowReceived(tvShowsResponse: List<ResultsTvShows>?) {
                var tvShows : TvShows? = null
                if (tvShowsResponse != null) {
                    for (response in tvShowsResponse){
                        if (response.id == tvId){
                            tvShows = TvShows(
                                    response.id,
                                    response.posterPath,
                                    response.originalLanguage,
                                    response.overview,
                                    response.originalName,
                                    response.firstAirDate,
                                    response.voteAverage)

                        }
                    }

                }
                tvWithResult.postValue(tvShows)

            }
        })

        return tvWithResult
    }
}