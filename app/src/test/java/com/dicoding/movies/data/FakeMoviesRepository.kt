package com.dicoding.movies.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.movies.Utils.AppExecutors
import com.dicoding.movies.data.source.local.LocalDataSource
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.data.source.remote.ApiResponse
import com.dicoding.movies.data.source.remote.RemoteDataSource
import com.dicoding.movies.data.source.remote.response.ResultsMovies
import com.dicoding.movies.data.source.remote.response.ResultsTvShows
import com.dicoding.movies.vo.Resource

class FakeMoviesRepository constructor(
private val remoteDataSource: RemoteDataSource,
private val localDataSource: LocalDataSource,
private val appExecutors: AppExecutors) : MoviesDataSource{

    override fun getAllMovies(): LiveData<Resource<PagedList<Movies>>> =
        object : NetworkBoundResource<PagedList<Movies>, List<ResultsMovies>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<Movies>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<Movies>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ResultsMovies>>> {
                return remoteDataSource.getAllMovies()
            }

            override fun saveCallResult(data: List<ResultsMovies>) {
                val movieList = ArrayList<Movies>()
                for (response in data){
                    val movies = Movies(
                        response.id,
                        response.posterPath,
                        response.originalLanguage,
                        response.overview,
                        response.title,
                        response.releaseDate,
                        false,
                        response.voteAverage)

                    movieList.add(movies)
                }
                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()

    override fun getAllTvShows(): LiveData<Resource<PagedList<TvShows>>> =
        object :NetworkBoundResource<PagedList<TvShows>, List<ResultsTvShows>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TvShows>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShows>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ResultsTvShows>>> {
                return remoteDataSource.getAllTvShows()
            }

            override fun saveCallResult(data: List<ResultsTvShows>) {
                val tvShowsList = ArrayList<TvShows>()
                for (response in data){
                    val tvShows = TvShows(
                        response.id,
                        response.posterPath,
                        response.originalLanguage,
                        response.overview,
                        response.originalName,
                        response.firstAirDate,
                        false,
                        response.voteAverage)

                    tvShowsList.add(tvShows)
                }
                localDataSource.insertTvShows(tvShowsList)
            }

        }.asLiveData()

    override fun getFavoritedMovies(): LiveData<PagedList<Movies>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedMovies(), config).build()
    }

    override fun getFavoritedTvShow(): LiveData<PagedList<TvShows>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedTvShows(), config).build()
    }

    override fun setFavoriteMovie(movies: Movies, state: Boolean) {
        appExecutors.diskIO().execute{
            localDataSource.setMoviesFavorite(movies, state)
        }

    }

    override fun setFavoriteTvShow(tvShows: TvShows, state: Boolean) {
        appExecutors.diskIO().execute{
            localDataSource.setTvShowFavorite(tvShows, state)
        }
    }

}