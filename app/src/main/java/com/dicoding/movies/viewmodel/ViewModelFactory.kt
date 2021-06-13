package com.dicoding.movies.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.movies.data.MoviesRepository
import com.dicoding.movies.Detail.DetailViewModel
import com.dicoding.movies.ListMovies.MoviesViewModel
import com.dicoding.movies.ListTv.TvViewModel
import com.dicoding.movies.di.Injection
import com.dicoding.movies.favoritemovies.FavoriteMoviesViewModel
import com.dicoding.movies.favoritetvshow.FavoriteTvShowViewModel

class ViewModelFactory private constructor(private val mMoviesRepository: MoviesRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance?: synchronized(this) {
                    ViewModelFactory(Injection.provideRepository(context)).apply { instance = this }
                    }
                }


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                return MoviesViewModel(mMoviesRepository) as T
            }

            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                return TvViewModel(mMoviesRepository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(mMoviesRepository) as T
            }

            modelClass.isAssignableFrom(FavoriteMoviesViewModel::class.java) -> {
                return FavoriteMoviesViewModel(mMoviesRepository) as T
            }

            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java) -> {
                return FavoriteTvShowViewModel(mMoviesRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}