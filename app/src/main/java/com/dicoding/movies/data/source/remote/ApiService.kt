package com.dicoding.movies.data.source.remote
import com.dicoding.movies.data.source.remote.response.MoviesResponse
import com.dicoding.movies.data.source.remote.response.TvShowsResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("3/movie/now_playing")
    fun getMovies (
        @Query("api_key") apiKey : String
    ): Call<MoviesResponse>


    @GET("3/tv/popular")
    fun getTvShows (
        @Query("api_key") apiKey: String
    ): Call<TvShowsResponse>
}