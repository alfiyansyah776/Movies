package com.dicoding.movies.Detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.movies.R
import com.dicoding.movies.data.source.local.entity.Movies
import com.dicoding.movies.data.source.local.entity.TvShows
import com.dicoding.movies.databinding.ActivityDetailBinding
import com.dicoding.movies.databinding.ContentDetailBinding
import com.dicoding.movies.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_MOVIES = "extra_movies"

    }

    private lateinit var detailMoviesViewModel : DetailViewModel
    private lateinit var detailBinding: ContentDetailBinding
    private lateinit var activitydetailBinding : ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitydetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailBinding = activitydetailBinding.detailContent

        setContentView(activitydetailBinding.root)

        setSupportActionBar(activitydetailBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        detailMoviesViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]


        val detailMovies = intent.getParcelableExtra<Movies>(EXTRA_MOVIES)
        val detailTvShows = intent.getParcelableExtra<TvShows>(EXTRA_TV)

        if (detailMovies != null) {
            populateMovies(detailMovies)
            supportActionBar?.setTitle(R.string.Movies)
        }

        if (detailTvShows != null) {
            populateTvShows(detailTvShows)
            supportActionBar?.setTitle(R.string.Tv)
        }

        }


    private fun populateMovies(movies: Movies){
        movies.let {
            detailBinding.textTitle.text = movies.title
            detailBinding.scorevalue.text = movies.score.toString()
            detailBinding.textSynopsis.text = movies.synopsis
            detailBinding.episodevalue.text = movies.language
            detailBinding.statusvalue.text = movies.aired


            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/"+movies.poster)
                .into(detailBinding.imageView)

            var statusFavorite = movies.favorite
            setStatusFavorite(statusFavorite)
            detailBinding.fabFavorite.setOnClickListener {
                if (statusFavorite){
                    statusFavorite = !statusFavorite
                    detailMoviesViewModel.setFavoriteMovies(movies, statusFavorite)
                    setStatusFavorite(statusFavorite)
                    Toast.makeText(this@DetailActivity, "Film telah dihapus pada daftar favorite", Toast.LENGTH_SHORT).show()
                } else {
                    statusFavorite = !statusFavorite
                    detailMoviesViewModel.setFavoriteMovies(movies, statusFavorite)
                    setStatusFavorite(statusFavorite)
                    Toast.makeText(this@DetailActivity, "Film telah ditambahkan kedalam daftar favorite", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

    private fun populateTvShows(tvShows: TvShows){
        tvShows.let {
            detailBinding.textTitle.text = tvShows.title
            detailBinding.scorevalue.text = tvShows.score.toString()
            detailBinding.textSynopsis.text = tvShows.synopsis
            detailBinding.episodevalue.text = tvShows.language
            detailBinding.statusvalue.text = tvShows.aired



            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/" + tvShows.poster)
                .into(detailBinding.imageView)

            var statusFavorite = tvShows.favorite
            setStatusFavorite(statusFavorite)
                detailBinding.fabFavorite.setOnClickListener {
                    if (statusFavorite){
                        statusFavorite = !statusFavorite
                        detailMoviesViewModel.setFavoriteTvShows(tvShows, statusFavorite)
                        setStatusFavorite(statusFavorite)
                        Toast.makeText(this@DetailActivity, "Serial Tv telah dihapus pada daftar favorite", Toast.LENGTH_SHORT).show()
                    } else {
                        statusFavorite = !statusFavorite
                        detailMoviesViewModel.setFavoriteTvShows(tvShows, statusFavorite)
                        setStatusFavorite(statusFavorite)
                        Toast.makeText(this@DetailActivity, "Serial Tv ini telah ditambahkan kedalam daftar favorite", Toast.LENGTH_SHORT).show()
                    }
                }


        }
    }




    private fun setStatusFavorite(statusFavorite: Boolean){
        if(statusFavorite){
            detailBinding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)

        } else {
            detailBinding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)

        }
    }
}