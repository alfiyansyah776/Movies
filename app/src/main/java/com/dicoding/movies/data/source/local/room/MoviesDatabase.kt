package com.dicoding.movies.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.data.source.local.entity.TvShows

@Database(entities = [Movies::class, TvShows::class],
version = 1,
exportSchema = false)

abstract class MoviesDatabase : RoomDatabase(){
    abstract fun moviesDao(): MoviesDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase =
            INSTANCE?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    "Movies.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}