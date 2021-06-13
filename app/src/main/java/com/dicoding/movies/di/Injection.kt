package com.dicoding.movies.di

import android.content.Context
import com.dicoding.movies.Utils.AppExecutors
import com.dicoding.movies.data.MoviesRepository
import com.dicoding.movies.data.source.remote.RemoteDataSource
import com.dicoding.movies.data.source.local.LocalDataSource
import com.dicoding.movies.data.source.local.room.MoviesDatabase
import com.dicoding.movies.data.source.remote.ApiConfig

object Injection {
    fun provideRepository(context: Context): MoviesRepository {

        val database = MoviesDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.getApiService())
        val localDataSource = LocalDataSource.getInstance(database.moviesDao())
        val appExecutors = AppExecutors()

        return MoviesRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}