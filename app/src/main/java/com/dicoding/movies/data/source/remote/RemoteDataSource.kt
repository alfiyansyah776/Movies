package com.dicoding.movies.data.source.remote

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.movies.Utils.EspressoidlingResources
import com.dicoding.movies.data.source.remote.response.ResultsMovies
import com.dicoding.movies.data.source.remote.response.ResultsTvShows
import com.dicoding.movies.data.source.remote.response.MoviesResponse
import com.dicoding.movies.data.source.remote.response.TvShowsResponse
import retrofit2.Call
import retrofit2.Response


class RemoteDataSource private constructor(private val apiService: ApiService){
    private val apiKey = "5c377cc06e2fc296835de427123e1c85"

    companion object{
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 5000
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
                instance?: synchronized(this){
                    instance?: RemoteDataSource(service)
                }
    }

    fun getAllMovies() : LiveData<ApiResponse<List<ResultsMovies>>> {
        //fungsi ini akan mengambil API dari TMDB yang mana akan mengambil movie yang sedang tayang
        var movies: List<ResultsMovies>
        val resultsMovies = MutableLiveData<ApiResponse<List<ResultsMovies>>>()
        val client = ApiConfig.getApiService().getMovies(apiKey)
        EspressoidlingResources.increment()
        client.enqueue(object : retrofit2.Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    EspressoidlingResources.decrement()
                    movies = response.body()?.results!!
                    resultsMovies.value = ApiResponse.success(movies)
                    //callback.onAllMoviesReceived(response.body()?.results)
                } else {
                    Log.e("ERROR", "YAH DATA HILANG")
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }

        })

        return resultsMovies

    }

    fun getAllTvShows() : LiveData<ApiResponse<List<ResultsTvShows>>> {
        //fungsi ini akan mengambil API dari TMDB yang mana akan mengambil tvShow yang sedang tayang
        var tvShows : List<ResultsTvShows>
        var resultsTvShows = MutableLiveData<ApiResponse<List<ResultsTvShows>>>()
        val client = ApiConfig.getApiService().getTvShows(apiKey)
        EspressoidlingResources.increment()
        client.enqueue(object : retrofit2.Callback<TvShowsResponse> {
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                if (response.isSuccessful) {
                    EspressoidlingResources.decrement()
                    tvShows = response.body()?.results!!
                    resultsTvShows.value = ApiResponse.success(tvShows)
                    //callback.onAllTvShowReceived(response.body()?.results)
                    Log.i("Data", "${tvShows.get(0)}")
                } else {
                    Log.e("ERROR", "YAH DATA HILANG")
                }

            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }

        })

        return resultsTvShows


    }




    /*

    fun getAllMovies(): LiveData<ApiResponse<List<ResultsMovies>>>{
        val resultsMovies = MutableLiveData<ApiResponse<List<ResultsMovies>>>()
        val mMovies = jsonHelper.loadMovies()
        handler.postDelayed({
            resultsMovies.value = ApiResponse.success(mMovies)
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultsMovies
    }

    fun getAllTvShows(): LiveData<ApiResponse<List<ResultsTvShows>>> {
        var resultsTvShows = MutableLiveData<ApiResponse<List<ResultsTvShows>>>()
        resultsTvShows.value = ApiResponse.success(jsonHelper.loadTvShows())
        return resultsTvShows
    }

    interface LoadMoviesCallback{
        fun onAllMoviesReceived(moviesResponse: List<ResultsMovies>?)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowsResponse: List<ResultsTvShows>?)
    } */

}